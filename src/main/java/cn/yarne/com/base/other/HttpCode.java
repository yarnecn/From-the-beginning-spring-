package cn.yarne.com.base.other;

import org.apache.taglibs.standard.resources.Resources;

public enum HttpCode {
    OK(200),
    MULTI_STATUS(207),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    LOGIN_FAIL(402),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    NOT_ACCEPTABLE(406),
    REQUEST_TIMEOUT(408),
    CONFLICT(409),
    GONE(410),
    LENGTH_REQUIRED(411),
    PRECONDITION_FAILED(412),
    ENTITY_TOO_LARGE(413),
    UNSUPPORTED_MEDIA_TYPE(415),
    TOO_MANY_CONNECTIONS(421),
    LOCKED(423),
    UNAVAILABLE_LEGAL(451),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    SERVICE_UNAVAILABLE(503),
    NOT_EXTENDED(510);

    private final Integer value;

    private HttpCode(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }

    public String msg() {
        return Resources.getMessage("HTTPCODE_" + this.value, new Object[0]);
    }

    public String toString() {
        return this.value.toString();
    }
}
