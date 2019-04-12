package gitlab.distribution.serialization;

public interface ISerializable {

    <T> byte[] serializable(T data);

    <T> T deserializable(byte[] bytes, Class<T> type);
}
