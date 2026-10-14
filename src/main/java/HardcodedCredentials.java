// https://documentation.blackduck.com/bundle/coverity-docs/page/checker-ref/checkers/FGH/hardcoded_credentials.html
// https://documentation.blackduck.com/bundle/coverity-docs/page/checker-ref/checkers/VWXYZ/weak_password_hash.html

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.crypto.spec.SecretKeySpec;

public class HardcodedCredentials {
    public static Connection getCon(String url) throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("db.properties")) {
            props.load(in);
        }
        return DriverManager.getConnection(url,
            /*username*/ props.getProperty("username"),
            /*password*/ props.getProperty("password"));
    }
    public static SecretKeySpec getKeySpec() throws UnsupportedEncodingException {
        String secret = "It's a secret to everybody.";
        SecretKeySpec keyspec = new SecretKeySpec(secret.getBytes("UTF-8"), "MD5");
        return(keyspec);
    }
}
