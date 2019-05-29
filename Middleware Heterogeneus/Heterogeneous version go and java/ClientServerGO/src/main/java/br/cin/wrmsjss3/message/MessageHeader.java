package br.cin.wrmsjss3.message;

import java.io.Serializable;

public class MessageHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    private String magic;
    private int version;
    private boolean byte_order;
    private int message_type;
    private long message_size;

    public MessageHeader(String magic, int version, boolean byteOrder, int messageType, long messageSize) {
        setMagic(magic);
        setVersion(version);
        setByte_order(byteOrder);
        setMessage_type(messageType);
        setMessage_size(messageSize);
    }

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isByte_order() {
        return byte_order;
    }

    public void setByte_order(boolean byte_order) {
        this.byte_order = byte_order;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public long getMessage_size() {
        return message_size;
    }

    public void setMessage_size(long message_size) {
        this.message_size = message_size;
    }

}
