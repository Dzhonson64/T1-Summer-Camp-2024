import com.exam.candidate.CandidateController;
import com.exam.candidate.CandidateControllerImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CandidateTest {

    private CandidateController candidateController = new CandidateControllerImpl();
    private String lastName = "Gafarova";

    private String firstName = "Aidara";

    private String email = "aydar-gafarov-07test@example.ru";

    private String role = "Разработчик Java";



    @Test
    public void signUp_test() {
        String signUp = candidateController.signUp(lastName, firstName, email, role);
        assertEquals("\"Данные внесены\"", signUp);
    }

    @Test
    public void getCode_test() {
        String code = candidateController.getCode(email);
        assertEquals(code.length(),34);
    }

    @Test
    public void setStatus_test() {
        String code = candidateController.getCode(email);
        String status = candidateController.setStatus(email, code);
        if (status.equals("Установлен статус increased") || status.equals("Статус increased зафиксирован. Задание выполнено")) {
            assertTrue(true);
        }
        else {
            assertFalse(false);
        }
    }


}
