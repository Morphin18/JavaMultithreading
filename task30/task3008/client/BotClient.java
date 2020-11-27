package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    public static void main(String[] args) {
        new BotClient().run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message != null) {
                if (message.contains(":")) {
                    String[] words = message.split(":");
                    String userName = words[0].trim();
                    String command = words[1].trim();
                    SimpleDateFormat dateFormat = null;
                    if (command.equals("дата")) {
                        dateFormat = new SimpleDateFormat("d.MM.yyyy");
                    } else if (command.equals("день")) {
                        dateFormat = new SimpleDateFormat("d");
                    } else if (command.equals("месяц")) {
                        dateFormat = new SimpleDateFormat("MMMM");
                    } else if (command.equals("год")) {
                        dateFormat = new SimpleDateFormat("yyyy");
                    } else if (command.equals("время")) {
                        dateFormat = new SimpleDateFormat("H:mm:ss");
                    } else if (command.equals("час")) {
                        dateFormat = new SimpleDateFormat("H");
                    } else if (command.equals("минуты")) {
                        dateFormat = new SimpleDateFormat("m");
                    } else if (command.equals("секунды")) {
                        dateFormat = new SimpleDateFormat("s");
                    }
                    if (dateFormat != null) {
                        sendTextMessage("Информация для " + userName + ": " +
                                dateFormat.format(new GregorianCalendar().getTime()));
                    }
                }
            }
        }
    }
}
