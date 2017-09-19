package com.sungwoo.aps.security;

import sun.security.krb5.Asn1Exception;
import sun.security.krb5.internal.AuthorizationDataEntry;
import sun.security.util.DerValue;

import java.io.IOException;

public class Auth02EntryPoint extends AuthorizationDataEntry {
    public Auth02EntryPoint(int i, byte[] bytes) {
        super(i, bytes);
    }

    public Auth02EntryPoint(DerValue derValue) throws Asn1Exception, IOException {
        super(derValue);
    }
}
