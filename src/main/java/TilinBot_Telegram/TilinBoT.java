package TilinBot_Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class TilinBoT extends TelegramLongPollingBot {


    @Override
    public String getBotUsername(){
        return "Tiliguilin";
    }

    @Override
    public String getBotToken(){
        return "7472338562:AAFYqK1b3A7ezdf7DhZ0sYp32Xaj_8gf5iA";
    }

    @Override
    public void onUpdateReceived(Update update){

        String[] parts = null;

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText().trim();  // Remueve espacios adicionales
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(chatId);

            // Inicializa 'parts' dividiendo el mensaje en palabras
            parts = messageText.split(" ");

            // Verifica que haya al menos un comando
            if (parts.length > 0) {
                switch (parts[0]) {
                    case "/info":
                        message.setText("Nombre: Angel Chavarria\n Carnet: 0905-23-3809\n Semestre: 4to. Semestre");
                        break;
                    case "/progra":
                        message.setText("Es una clase donde aprendes que si no sabes programar te vas a morir de hambre y que tienes que ver a las chicas en la cafetería.");
                        break;
                    case "/hola":
                        String nombre = update.getMessage().getFrom().getFirstName();
                        String apellido = update.getMessage().getFrom().getLastName();
                        String fecha = java.time.LocalDate.now().toString();
                        message.setText("Hola, " + nombre + " " + apellido + ", que queres que venis a fregar hoy, " + fecha);
                        break;
                    case "/cam":
                        if (parts.length > 1) {  // Verifica que hay al menos dos elementos
                            try {
                                double cantidad = Double.parseDouble(parts[1]);
                                double tipoCambio = 7.85;  // Tipo de cambio fijo para el ejemplo
                                double resultado = tipoCambio * cantidad;
                                message.setText("El cambio de EUR " + cantidad + " es de Q " + resultado);
                            } catch (NumberFormatException e) {
                                message.setText("Por favor, ingrese un número válido.");
                            }
                        } else {
                            message.setText("Por favor, ingrese la cantidad en euros para convertir.");
                        }
                        break;
                    case "/grupal":
                        if (parts.length > 1) {
                            String mensajeGrupal = messageText.substring(messageText.indexOf(" ") + 1);

                            List<Long> listaDeChatIds = List.of(6709392176L, 6922425377L, 6957944438L);  // Lista de chat IDs

                            for (Long chatIdCompanero : listaDeChatIds) {
                                SendMessage mensajeGrupalBot = new SendMessage();
                                mensajeGrupalBot.setChatId(chatIdCompanero);
                                mensajeGrupalBot.setText(mensajeGrupal);

                                try {
                                    execute(mensajeGrupalBot);
                                    System.out.println("Mensaje enviado a: " + chatIdCompanero);
                                } catch (TelegramApiException e) {
                                    System.out.println("Error al enviar el mensaje al chat_id 😞😞" + chatIdCompanero);
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            message.setText("Por favor, ingrese un mensaje después del comando /grupal.");
                        }
                        break;
                    default:
                        message.setText("Comando inválido😵‍💫");
                }
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
