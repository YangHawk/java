package xyz.itwill.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//프로퍼티 파일 리더 클래스 -> EmailSender에서 프로퍼티 파일을 읽어오는데 사용
public class EmailPropertiesReader {
    private static final String PROPERTIES_FILE = "email.properties";

    public static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = EmailPropertiesReader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException(PROPERTIES_FILE + " 파일을 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
