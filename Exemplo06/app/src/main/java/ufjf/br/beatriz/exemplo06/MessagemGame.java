package ufjf.br.beatriz.exemplo06;

public class MessagemGame {

    public String returnMessage(String resultado, Integer valorJogadaHumano, Integer valorJogadaPc){
        String message = "";
        switch (resultado){
            case "EMPATE":
                return "Empate!";
            default:
                switch (valorJogadaHumano){
                    case 0:
                        message = pedraAction(valorJogadaPc);
                        break;
                    case 1:
                        message = papelAction(valorJogadaPc);
                        break;
                    case 2:
                        message = tesouraAction(valorJogadaPc);
                        break;
                    case 3:
                        message = spockAction(valorJogadaPc);
                        break;
                    case 4:
                        message = lagartoAction(valorJogadaPc);
                        break;
                }
        }
        return message;
    }

    private String pedraAction(Integer valorJogadaPc)
    {
        String message = "";
        switch (valorJogadaPc){
            case 1:
                message =  "Papel cobre pedra!";
                break;
            case 2:
                message = "Pedra quebra tesoura!";
                break;
            case 3:
                message = "Spock vaporiza pedra!";
                break;
            case 4:
                message = "Pedra esmaga lagarto!";
                break;
        }
        return  message;
    }

    private String papelAction(Integer valorJogadaPc)
    {
        String message = "";
        switch (valorJogadaPc){
            case 0:
                message =  "Papel cobre pedra!";
                break;
            case 2:
                message = "Tesoura corta papel!";
                break;
            case 3:
                message = "Papel refuta Spock!";
                break;
            case 4:
                message = "Lagarto come papel!";
                break;
        }
        return  message;
    }

    private String tesouraAction(Integer valorJogadaPc)
    {
        String message = "";
        switch (valorJogadaPc){
            case 0:
                message =  "Pedra quebra tesoura!";
                break;
            case 1:
                message = "Tesoura corta papel!";
                break;
            case 3:
                message = "Spock esmaga tesoura!";
                break;
            case 4:
                message = "Tesoura decapita lagarto!";
                break;
        }
        return  message;
    }

    private String spockAction(Integer valorJogadaPc)
    {
        String message = "";
        switch (valorJogadaPc){
            case 0:
                message =  "Spock vaporiza pedra!";
                break;
            case 1:
                message = "Papel refuta Spock!";
                break;
            case 2:
                message = "Spock esmaga tesoura!";
                break;
            case 4:
                message = "Lagarto envenena Spock!";
                break;
        }
        return  message;
    }

    private String lagartoAction(Integer valorJogadaPc)
    {
        String message = "";
        switch (valorJogadaPc){
            case 0:
                message =  "Pedra esmaga lagarto!";
                break;
            case 1:
                message = "Lagarto come papel!";
                break;
            case 2:
                message = "Tesoura decapita lagarto!";
                break;
            case 3:
                message = "Lagarto envenena Spock!";
                break;
        }
        return  message;
    }
}
