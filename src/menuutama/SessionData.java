package menuutama;

public class SessionData {
    private static String idAkun;

    public static String getIdAkun() {
        return idAkun;
    }

    public static void setIdAkun(String idAkun) {
        SessionData.idAkun = idAkun;
    }
}
