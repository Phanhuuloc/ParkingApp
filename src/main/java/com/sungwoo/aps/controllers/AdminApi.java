package com.sungwoo.aps.controllers;

/**
 * @author phloc
 */
public interface AdminApi {

    /**
     * Handle request admin page for crate new are
     *
     * @return html name
     */
    String requestAdmin();

    /**
     * Handle request for home page
     *
     * @return
     */
    String requestHome();
}
