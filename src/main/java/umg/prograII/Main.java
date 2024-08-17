package umg.prograII;

import TilinBot_Telegram.TilinBoT;
import TilinBot_Telegram.tareaBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try{
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            TilinBoT tilin = new TilinBoT();
            botsApi.registerBot(tilin);
            System.out.println("El bot esta funcionando...");
        }catch (Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
    }
}