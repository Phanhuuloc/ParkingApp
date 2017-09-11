package com.sungwoo.aps.services;

import com.sungwoo.aps.commons.ApsProperties;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class TCPConnection {
    private final static Logger LOGGER = Logger.getLogger(TCPConnection.class);

    private static final byte PACKET_SOURCE = 0x03;
    private static final byte PACKET_DES = 0x02;

    private static final int ALLOC_CRC = 4;
    private static final int ALLOC_HEADER = 4;
    private static final int ALLOC_DATA_CAR = 4;
    private static final int ALLOC_DATA_PARKING_AREA = 4;

    private final int[] ids;
    private ApsProperties properties;

    private TCPConnection(Builder builder) {
        this.properties = builder.properties;
        this.ids = builder.ids;
    }

    static Builder init(ApsProperties properties) {
        return new Builder(properties);
    }

    private byte[] buildTCPRequest() {
        if (1 == ids.length) {
            return buildPacket(Command.CAR_CALL, ALLOC_DATA_CAR);
        } else if (2 == ids.length) {
            return buildPacket(Command.CAR_PARKING, ALLOC_DATA_CAR + ALLOC_DATA_PARKING_AREA);
        } else {
            return null;
        }
    }

    private byte[] buildPacket(Command command, int dataSize) {
        LOGGER.info("Build execute command on socket: " + command.description);

        int ALLOC_PACKET = ALLOC_CRC + ALLOC_HEADER + dataSize;

        byte[] header = ByteBuffer.allocate(ALLOC_HEADER)
                .put(command.value)
                .put((byte) (ALLOC_PACKET))
                .put(PACKET_SOURCE)
                .put(PACKET_DES)
                .array();

        ByteBuffer buff = ByteBuffer.allocate(dataSize);
        for (int id : ids) {
            buff.put(intToByteArray(id));
        }
        byte[] data = buff.array();


        byte[] packet = ByteBuffer.allocate(ALLOC_HEADER + dataSize)
                .put(header)
                .put(data)
                .array();

        Checksum checksum = new CRC32();
        checksum.update(packet, 0, packet.length);
        int checksumValue = (int) checksum.getValue();
        LOGGER.info("CRC: " + checksumValue);

        byte req[] = ByteBuffer.allocate(ALLOC_PACKET)
                .put(intToByteArray(checksumValue))
                .put(packet)
                .array();

        StringBuilder str = new StringBuilder("Request is: ");
        for (byte b : req) {
            str.append(String.format("0x%x ", b));
        }
        LOGGER.info("byte array" + Arrays.toString(req));
        LOGGER.info(str);

        return req;
    }

    private byte[] intToByteArray(int a) {
        byte[] ret = new byte[4];
        ret[0] = (byte) (a & 0xFF);
        ret[1] = (byte) ((a >> 8) & 0xFF);
        ret[2] = (byte) ((a >> 16) & 0xFF);
        ret[3] = (byte) ((a >> 24) & 0xFF);
        return ret;
    }

    private static int byteArrayToInt(byte[] b) {
        return (b[3] & 0xFF) << 24 |
                (b[2] & 0xFF) << 16 |
                (b[1] & 0xFF) << 8 |
                (b[0] & 0xFF);
    }

    Permission execute() {
        Permission res = null;
        String tcpServer = properties.getTcp().getServer();
        int tcpPort = properties.getTcp().getPort();
        int tcpTimeout = properties.getTcp().getTimeout();
        LOGGER.info(String.format("Execute parking command on socket %s:%d", tcpServer, tcpPort));

        Socket socket = new Socket();
        try {
            socket = new Socket(tcpServer, tcpPort);
            socket.setSoTimeout(tcpTimeout);

            LOGGER.info("Server socket is open!!!");
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            InputStream stream = socket.getInputStream();

            byte[] req = buildTCPRequest();
            assert req != null;
            os.write(req);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[16];
            int n = stream.read(buf);
            baos.write(buf, 0, n);

            byte data[] = baos.toByteArray();
            StringBuilder str = new StringBuilder("Response is: ");
            for (byte b : data) {
                str.append(String.format("0x%x ", b));
            }
            LOGGER.info(str.toString());

            Checksum checksum = new CRC32();
            checksum.update(data, 4, data.length - 4);
            int checksumValue = (int) checksum.getValue();

            byte[] crc = Arrays.copyOfRange(data, 0, 4);
            LOGGER.info(String.format("Check CRC. Receive: %d, check %d", byteArrayToInt(crc), checksumValue));

            int permission = data[data.length - 1];
            if (Permission.ALLOW.value == permission) {
                res = Permission.ALLOW;
            } else {
                res = Permission.DENY;
            }

            socket.close();
        } catch (SocketTimeoutException ex) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("Response is:" + res);
        return res;
    }

    enum Command {
        CAR_PARKING((byte) 0x16, "Parking message"),
        CAR_CALL((byte) 0x18, "Car call message");

        private final byte value;
        private final String description;

        Command(byte value, String description) {
            this.value = value;
            this.description = description;
        }
    }

    public enum Permission {
        ALLOW((byte) 0x03, "ALLOW"),
        DENY((byte) 0x04, "DENY");

        private final byte value;
        private final String des;

        Permission(byte value, String des) {
            this.value = value;
            this.des = des;
        }

        public byte getValue() {
            return value;
        }

        public String getDes() {
            return des;
        }
    }

    public static final class Builder {
        private int[] ids;
        private ApsProperties properties;

        private Builder(ApsProperties properties) {
            this.properties = properties;
        }

        TCPConnection build() {
            return new TCPConnection(this);
        }

        public Builder request(int... ids) {
            this.ids = ids;
            return this;
        }
    }
}
