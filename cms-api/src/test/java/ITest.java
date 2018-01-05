import com.google.gson.Gson;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.Menu;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;


public class ITest {


    @Test
    public void IT(){
        Menu menu = new Menu();
        menu.setId(1);
        menu.setName("添加");
        List<Menu> menus = new LinkedList<>();
        menus.add(menu);
        menu = new Menu();
        menu.setId(2);
        menu.setName("删除");
        menus.add(menu);
        Response<List<Menu>> response = new Response<>();
        response.setStatus(Response.SUCCESS);
        response.setMessage(menus);
        System.out.println(new Gson().toJson(response));
    }
}
