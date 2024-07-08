import org.junit.Test;
import ru.t1.summer.camp.roles.RolesController;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RolesTest
{
    @Test
    public void get_roles_test() throws IOException
    {
        RolesController rolesController = new RolesController();
        List<String> roles = rolesController.getRoles();
        assertEquals("Разработчик Java", roles.get(1));
    }

}
