package ru.t1.summer.camp.dataUtil;

import ru.t1.summer.camp.dto.SetStatusDto;
import ru.t1.summer.camp.dto.SignUpDto;

public final class DataUtil {
    private DataUtil() {

    }

    public static SignUpDto signUpDto() {
        return new SignUpDto(
                "Фамилия",
                "Имя",
                "email",
                "Разработчик Java"
        );

    }

    public static SetStatusDto setStatusDto() {
        return new SetStatusDto(
                "QTDSFSFET123AFWR",
                "increased"
        );
    }
}
