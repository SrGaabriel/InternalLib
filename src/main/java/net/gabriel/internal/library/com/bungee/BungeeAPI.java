package net.gabriel.internal.library.com.bungee;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;
import java.util.*;

/*
* This is not mine (xGabriel), i took it off from a developer
* that I don't remember the name, futurely i will update the repository
* with a BungeeAPI that I did.
*/

@SuppressWarnings("all")
public class BungeeAPI {

    private boolean debug;
    private String currentServer;
    private Map<String, SimpleServer> servers;
    private Map<String, SimplePlayer> players;
    private PluginMessageListener listener;

    public BungeeAPI(JavaPlugin plugin, String server, PluginMessageListener listener) {
        this.debug = false;
        this.currentServer = server;
        this.servers = new HashMap<>();
        this.players = new HashMap<>();
        this.listener = listener;
    }

    public String getCurrentServer() {
        return currentServer;
    }

    public int getPlayerCount(String server) {
        return getServer(server).playerCount;
    }

    public List<String> getPlayers(String server) {
        return getServer(server).players;
    }

    public String getPlayerServer(String player) {
        return getPlayer(player).server;
    }

    public void log(String message) {

        if (debug)
            Bukkit.getConsoleSender().sendMessage("§7[§8BungeeAPI§7] §f" + message);
    }

    public SimpleServer getServer(String serverName) {
        SimpleServer server = servers.get(serverName);
        if (server == null) {
            server = new SimpleServer();
            server.name = serverName;
            servers.put(serverName, server);
        }
        return server;
    }

    public SimplePlayer getPlayer(String playerName) {
        SimplePlayer player = players.get(playerName);
        if (player == null) {
            player = new SimplePlayer();
            player.name = playerName;
            players.put(playerName, player);
        }
        return player;
    }

    Player getFirstPlayer() {
        return Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
    }

    public Plugin getInstance() {
        return JavaPlugin.getProvidingPlugin(BungeeAPI.class);
    }

    void sendMessage(ByteArrayDataOutput message) {
        Bukkit.getServer().sendPluginMessage(getInstance(), "BungeeCord", message.toByteArray());
//		sendMessage(getFirstPlayer(), message);
    }

    void sendMessage(Player player, ByteArrayDataOutput message) {
        player.sendPluginMessage(getInstance(), "BungeeCord", message.toByteArray());
    }

    public void forwardToServer(String subChannel, String server, Object... data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Forward"); // So BungeeCord knows to forward it
        out.writeUTF(server);
        out.writeUTF(subChannel); // The channel name to check if this your data

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.writeInt(data.length);
            for (int i = 0; i < data.length; i++) {
                msgout.writeUTF(data[i].toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        sendMessage(out);
    }

    public void forwardToPlayer(String playerName, String subChannel, Object... data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ForwardToPlayer");
        out.writeUTF(playerName);
        out.writeUTF(subChannel);

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.writeInt(data.length);
            for (int i = 0; i < data.length; i++) {
                msgout.writeUTF(data[i].toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } // You can do anything you want with msgout

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        sendMessage(out);
    }

    public void unregister() {
        Plugin plugin = getInstance();
        Bukkit.getMessenger().unregisterIncomingPluginChannel(plugin, "BungeeCord", listener);
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public void connectToServer(Player player, String server) {
        log("CONNECT TO §E" + server + "§F PLAYER §E" + player.getName());
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);

        sendMessage(player, out);
    }

    public void connectToServer(String playerName, String server) {
        log("CONNECT TO §E" + server + "§F PLAYER §E" + playerName);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(playerName);
        out.writeUTF(server);
        sendMessage(out);
    }

    String[] readMessage(ByteArrayDataInput data) {
        short len = data.readShort();
        byte[] msgbytes = new byte[len];

        data.readFully(msgbytes);
        String[] result = null;
        DataInputStream msgIn = new DataInputStream(new ByteArrayInputStream(msgbytes));
        try {
            result = new String[msgIn.readInt()];
            for (int i = 0; i < result.length; i++) {
                String somedata = msgIn.readUTF();
                result[i] = somedata;
            }

        } catch (IOException e) {
            e.printStackTrace();
            result = new String[0];
        }
        return result;
    }

    public void kickPlayer(String playerName, String reason) {
        log("KICK §e" + playerName + "'§E REASON §F" + reason);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(playerName);
        out.writeUTF(reason);

        sendMessage(out);
    }

    public void sendMessage(String playerName, String message) {
        log("CHAT §E" + playerName + "§F MESSAGE §E" + message);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(playerName);
        out.writeUTF(message);
        sendMessage(out);
    }

    public boolean isServersRequest(String request) {
        return (request.equals("GetServers"));
    }

    public boolean isPlayerIpRequest(String request) {

        return request.equals("IP");
    }

    public boolean isServerRequest(String request) {
        return request.equals("GetServer");
    }

    public boolean isPlayerListRequest(String request) {
        return request.equals("PlayerList");
    }

    public boolean isPlayerCountRequest(String request) {
        return request.equals("PlayerCount");
    }

    public boolean isUUIDRequest(String request) {

        return request.equals("UUID");
    }

    public boolean isUUIDOtherRequest(String request) {

        return request.equals("UUIDOther");
    }

    public boolean isServerIpRequest(String request) {

        return request.equals("ServerIP");
    }

    public void requestServersNames() {
        log("REQUEST SERVERS NAMES");
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        sendMessage(out);
    }

    public void requestServerIp(String server) {
        log("REQUEST IP FROM SERVER §e" + server);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ServerIP");
        out.writeUTF(server);
        sendMessage(out);
    }

    public void requestPlayerCount(String server) {
        log("REQUEST PLAYER COUNT FROM SERVER §e" + server);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        sendMessage(out);
    }

    public void requestPlayerId(Player player) {
        log("REQUEST ID FROM PLAYER §e" + player.getName());
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("UUID");

        sendMessage(player, out);
    }

    public void requestPlayerId(String playerName) {
        log("REQUEST ID FROM TARGET PLAYER §e" + playerName);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("UUIDOther");
        out.writeUTF(playerName);
        sendMessage(out);
    }

    public void requestPlayerIp(Player player) {
        log("REQUEST IP FROM PLAYER §e" + player.getName());
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("IP");
        player.sendPluginMessage(getInstance(), "BungeeCord", out.toByteArray());
    }

    public void requestPlayerList(String server) {
        log("REQUEST PLAYERS FROM SERVER §E" + server);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF(server);
        sendMessage(out);
    }

    public void requestCurrentServer() {
        log("REQUEST CURRENT SERVER");
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServer");
        sendMessage(out);
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private class BukkitListener implements PluginMessageListener {

        @Override
        public void onPluginMessageReceived(String channel, Player player, byte[] message) {

            if (!channel.equals("BungeeCord")) {
                return;
            }
            try {
                ByteArrayDataInput data = ByteStreams.newDataInput(message);
                String request = data.readUTF();
                if (isPlayerCountRequest(request)) {
                    String server = data.readUTF();
                    getServer(server).playerCount = data.readInt();
                } else if (isServersRequest(request)) {
                    String[] servers = data.readUTF().split(", ");
                    log("§aRESPONSE SERVERS: §F" + Arrays.asList(servers));
                    for (String server : servers) {
                        getServer(server);
                    }
                } else if (isPlayerListRequest(request)) {
                    String server = data.readUTF();
                    String[] players = data.readUTF().split(", ");
                    List<String> list = Arrays.asList(players);
                    getServer(server).players = list;
                    log("§aRESPONSE PLAYERS FROM SERVER §F" + server + " : " + list);
                } else if (isServerRequest(request)) {
                    currentServer = data.readUTF();
                } else if (isServerIpRequest(request)) {
                    String serverName = data.readUTF();
                    String ip = data.readUTF();
                    int port = data.readUnsignedShort();
                    SimpleServer server = getServer(serverName);
                    server.host = ip;
                    server.port = port;
                } else if (isUUIDRequest(request)) {
                    getPlayer(player.getName()).uuid = data.readUTF();
                } else if (isUUIDOtherRequest(request)) {
                    String playerName = data.readUTF();
                    getPlayer(playerName).uuid = data.readUTF();
                } else if (isPlayerIpRequest(request)) {
                    String ip = data.readUTF();
                    int port = data.readInt();
                    SimplePlayer fake = getPlayer(player.getName());
                    fake.host = ip;
                    fake.port = port;
                }

            } catch (Exception e) {
                // e.printStackTrace();
                log("Deu erro na linha " + e.getLocalizedMessage());
            }
        }

    }

    public class SimpleServer {
        private String name;
        private List<String> players = new ArrayList<>();
        private int playerCount = 0;
        private String host;
        private int port;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getPlayers() {
            return players;
        }

        public void setPlayers(List<String> players) {
            this.players = players;
        }

        public int getPlayerCount() {
            return playerCount;
        }

        public void setPlayerCount(int playerCount) {
            this.playerCount = playerCount;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

    }

    public class SimplePlayer {

        private String server;
        private String name;
        private String uuid;
        private String host;
        private int port;

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

    }
}
