package me.yarocks.fortuneblocks;

 import org.bukkit.Bukkit;
 import org.bukkit.command.ConsoleCommandSender;

 public class Output {

 private static ConsoleCommandSender ccs = Bukkit.getConsoleSender();

     public static void print(String message) {
         ccs.sendMessage(message + "");
     }
 }

