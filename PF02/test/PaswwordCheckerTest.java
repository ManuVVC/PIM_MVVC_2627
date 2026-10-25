import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaswwordCheckerTest {
    //Contraseña correcta. Codigo esperado 0.
    @Test
    public void testCorrecto(){
        PasswordChecker pwChecker = new PasswordChecker();
        pwChecker.setCheckDigits(true);
        pwChecker.setCheckLowers(true);
        pwChecker.setCheckUppers(true);
        Assertions.assertEquals(pwChecker.PASSWORD_OK,pwChecker.check("ManuelVC12","ManuelVC12"));
    }

    //Longitud menor que el minimo por defecto, 8. Codigo esperado 1.
    @Test
    public void testLongitudMinima(){
        PasswordChecker pwChecker = new PasswordChecker();
        Assertions.assertEquals(pwChecker.ERR_LENGTH,pwChecker.check("manu","manu"));
    }

    //Activar control de numeros. Codigo esperao 2.
    @Test
    public void testNumeros(){
        PasswordChecker pwChecker = new PasswordChecker();
        pwChecker.setCheckDigits(true);
        Assertions.assertEquals(pwChecker.ERR_DIGITS,pwChecker.check("ManuelVC","ManuelVC"));
    }

    //Activar control de MAYUSCULAS. Codigo esperado 3.
    @Test
    public void testMayusculas(){
        PasswordChecker pwChecker = new PasswordChecker();
        pwChecker.setCheckUppers(true);
        Assertions.assertEquals(pwChecker.ERR_UPPERS, pwChecker.check("manuelvc12","manuelvc12"));
    }

    //Activar control de minusculas. Codigo esperado 4.
    @Test
    public void testMinusculas(){
        PasswordChecker pwChecker = new PasswordChecker();
        pwChecker.setCheckLowers(true);
        Assertions.assertEquals(pwChecker.ERR_LOWERS,pwChecker.check("MANUELVC12","MANUELVC12"));
    }

    //Contraseña vacia o en blanco. Codigo esperado 5.
    @Test
    public void testVacioBlanco() {
        PasswordChecker pwChecker = new PasswordChecker();
        Assertions.assertEquals(pwChecker.ERR_BLANK,pwChecker.check("",""));
        Assertions.assertEquals(pwChecker.ERR_BLANK,pwChecker.check("  ","  "));
    }

    //Prueba de coinciencia. Codigo esperado 6.
    @Test
    public void testCoincidencia(){
        PasswordChecker pwChecker = new PasswordChecker();
        Assertions.assertEquals(pwChecker.ERR_NO_MATCH, pwChecker.check("ManuelVC12","ManuelVC21"));
    }

    //Prueba esteblecer minimo erroneo. Lanza IlegalExceptio
    @Test
    public void testErrorMinimo (){
        PasswordChecker pwChecker = new PasswordChecker();
        try { pwChecker.setMinLength(0);}
        catch (IllegalArgumentException e){
            Assertions.assertEquals(pwChecker.ERR_INVALID_MIN_LENGTH,e.getMessage());
        }
        Assertions.fail();
    }

    //Prueba esteblecer maximo erroneo. Lanza IlegalExceptio
    @Test
    public void testErrorMaximo (){
        PasswordChecker pwChecker = new PasswordChecker();
        try {
            pwChecker.setMinLength(6);
            pwChecker.setMaxLength(5);
        }
        catch (IllegalArgumentException e){
            Assertions.assertEquals(pwChecker.ERR_INVALID_MAX_LENGTH,e.getMessage());
        }
        Assertions.fail();
    }

}
