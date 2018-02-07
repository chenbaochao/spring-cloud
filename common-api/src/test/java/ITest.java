import org.junit.Test;

public class ITest {

    @Test
    public void Itest(){
        String userJwt = "eyAiYWxnIjogIkhTMjU2IiwidHlwIjogIkpXVCJ9.eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiYWRtaW4iLCJleHBpcnlEYXRlIjoxNTE0NjAzOTQ3OTg2fQ==.0EPgGrdAdH+q7X2iGTkXNFtIp9MFlr3++oHz4SeX19g=";
        String []jwtArray = userJwt.split("\\.");
        System.out.println(jwtArray[1]);
    }
}
