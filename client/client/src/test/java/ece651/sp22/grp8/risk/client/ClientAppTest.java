/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ece651.sp22.grp8.risk.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

import ece651.sp22.grp8.risk.AttackPacket;
import ece651.sp22.grp8.risk.CommitPacket;
import ece651.sp22.grp8.risk.GamePrompt;
import ece651.sp22.grp8.risk.HumanPlayer;
import ece651.sp22.grp8.risk.Leave;
import ece651.sp22.grp8.risk.LeavePacket;
import ece651.sp22.grp8.risk.Map;
import ece651.sp22.grp8.risk.MapFactory;
import ece651.sp22.grp8.risk.MapPacket;
import ece651.sp22.grp8.risk.MovePacket;
import ece651.sp22.grp8.risk.Player;
import ece651.sp22.grp8.risk.Utility;
import ece651.sp22.grp8.risk.server.ServerApp;

class ClientAppTest {
//  @Disabled
//  @Test
//  @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
//  void test_App() throws Exception {
//    Thread th = new Thread() {
//      @Override()
//      public void run() {
//        ServerApp.main(new String[0]);
//      }
//    };
//
//    Thread c2 = createClient("input-client2.txt");
//
//    th.start();
//    Thread.sleep(1000);
//
//    c2.start();
//    Thread.sleep(100);
//
//
//    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//    PrintStream out = new PrintStream(bytes, true);
//    InputStream input = getClass().getClassLoader().getResourceAsStream("input-client3.txt");
//    assertNotNull(input);
//    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output-client3.txt");
//    assertNotNull(expectedStream);
//
//    InputStream oldIn = System.in;
//    PrintStream oldOut = System.out;
//    String expected = new String();
//
//    try {
//      System.setIn(input);
//      System.setOut(out);
//      String[] args = { "127.0.0.1", "8888" };
//      ClientApp.main(args);
//
//      expected = new String(expectedStream.readAllBytes());
//      // assertEquals(expected, bytes.toString());
//    }
//
//    catch (IOException e) {
//    }
//
//    finally {
//      System.setIn(oldIn);
//      System.setOut(oldOut);
//    }
//
//    c2.join();
//    th.interrupt();
//    th.join();
//  }
//
//  @Disabled
//  @Test
//  public void test_playOneTurn() {
//    Thread server = new Thread() {
//      @Override()
//      public void run() {
//        try {
//          ServerSocket server = new ServerSocket(8889);
//          Socket connect = server.accept();
//          Utility u = new Utility();
//          MovePacket mp = (MovePacket) u.recvPacket(connect);
//          u.sendMsg(connect, GamePrompt.OK);
//          AttackPacket ap = (AttackPacket) u.recvPacket(connect);
//          u.sendMsg(connect, GamePrompt.OK);
//          CommitPacket c = (CommitPacket) u.recvPacket(connect);
//          server.close();
//        } catch (Exception e) {
//        }
//      }
//    };
//
//    try {
//      server.start();
//      Thread.sleep(1000);
//      String choice = "2\n2\n2\nM\nmordor\n1\nhogwarts\nA\nHogwarts\n1\nScadrial\nD\n";
//      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//      ClientApp app = new ClientApp("localhost", 8889, new BufferedReader(new StringReader(choice)),
//          new PrintStream(bytes));
//      app.game = createClientGameWithNum(choice, bytes, 2);
//      app.game.initialUnitsPhase();
//      bytes.reset();
//      app.playOneTurn();
//      app.playOneTurn();
//      app.playOneTurn();
//      server.interrupt();
//      server.join();
//
//      String expected = "You are the Red player, what would you like to do?\n" + "(M)ove\n" + "(A)ttack\n" + "(D)one\n"
//          + "You can only move between your own territories. The move path must be form by adjacent territories owned by yourself.\n"
//          + "Please enter the original territory (letter case is no matter).\n"
//          + "Please enter the number of units you want to move.\n"
//          + "Please enter the destination territory (letter case is no matter).\n" + "Red player :\n"
//          + "--------------\n" + "2 unites in Elantris (next to: Narnia,Midkemia,Scadrial,Roshar)\n"
//          + "1 unites in Mordor (next to: Scadrial,Hogwarts)\n" + "2 unites in Narnia (next to: Elantris,Midkemia)\n"
//          + "3 unites in Hogwarts (next to: Mordor,Scadrial,Roshar)\n" + "Green player :\n" + "--------------\n"
//          + "0 unites in Scadrial (next to: Elantris,Midkemia,Oz,Mordor,Hogwarts,Roshar)\n"
//          + "0 unites in Midkemia (next to: Narnia,Elantris,Scadrial,Oz)\n"
//          + "0 unites in Oz (next to: Midkemia,Scadrial)\n"
//          + "0 unites in Roshar (next to: Elantris,Scadrial,Hogwarts)\n"
//          + "You are the Red player, what would you like to do?\n" + "(M)ove\n" + "(A)ttack\n" + "(D)one\n"
//          + "You can only attack directly adjacent territories.\n"
//          + "Please enter the original territory (letter case is no matter).\n"
//          + "Please enter the number of units you want to attack.\n"
//          + "Please enter the destination territory (letter case is no matter).\n" + "Red player :\n"
//          + "--------------\n" + "2 unites in Elantris (next to: Narnia,Midkemia,Scadrial,Roshar)\n"
//          + "1 unites in Mordor (next to: Scadrial,Hogwarts)\n" + "2 unites in Narnia (next to: Elantris,Midkemia)\n"
//          + "2 unites in Hogwarts (next to: Mordor,Scadrial,Roshar)\n" + "Green player :\n" + "--------------\n"
//          + "0 unites in Scadrial (next to: Elantris,Midkemia,Oz,Mordor,Hogwarts,Roshar)\n"
//          + "0 unites in Midkemia (next to: Narnia,Elantris,Scadrial,Oz)\n"
//          + "0 unites in Oz (next to: Midkemia,Scadrial)\n"
//          + "0 unites in Roshar (next to: Elantris,Scadrial,Hogwarts)\n" + "In this turn, you have been:\n"
//          + "Attack Scadrial with 1 units.\n" + "You are the Red player, what would you like to do?\n" + "(M)ove\n"
//          + "(A)ttack\n" + "(D)one\n";
//      assertEquals(expected, bytes.toString());
//    } catch (Exception e) {
//    }
//  }
//
//  @Disabled
//  @Test
//  public void test_lostPhase() {
//    Thread server = new Thread() {
//      @Override()
//      public void run() {
//        try {
//          ServerSocket s = new ServerSocket(8889);
//          Socket connect = s.accept();
//          Utility u = new Utility();
//          u.recvPacket(connect);
//          Leave l = new Leave(0, false);
//          LeavePacket lp = new LeavePacket(l);
//          MapFactory mp = new MapFactory();
//          MapPacket mpp = new MapPacket(mp.generateMapWithPlayer(2));
//          u.sendPacket(connect, lp);
//          u.sendPacket(connect, mpp);
//
//          Leave l2 = new Leave(0, true);
//          lp.packPacket(l2);
//          u.sendPacket(connect, lp);
//          s.close();
//        } catch (Exception e) {
//        }
//      }
//    };
//
//    try {
//      server.start();
//      Thread.sleep(1000);
//      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//      ClientApp app = new ClientApp("localhost", 8889, new BufferedReader(new StringReader("M\nD\nC\n")),
//          new PrintStream(bytes));
//      app.game = createClientGameWithNum("M\nD\nC\n", bytes, 2);
//      assertNotEquals(null, app.inputReader);
//
//      app.losePhase();
//      app.losePhase();
//      server.interrupt();
//      server.join();
//      String expected = "Oops! you lose the game.\n" + "Would you like to (C)ontinue to watch the game?\n"
//          + "Or you can (D)isconnect with the server\n" + "I don't understand.Please enter a valid input.\n"
//          + "Oops! you lose the game.\n" + "Would you like to (C)ontinue to watch the game?\n"
//          + "Or you can (D)isconnect with the server\n" + "Bye, have a good day!\n" + "Oops! you lose the game.\n"
//          + "Would you like to (C)ontinue to watch the game?\n" + "Or you can (D)isconnect with the server\n"
//          + "You are welcomed to watch the rest of the game.\n" + "Red player :\n" + "--------------\n"
//          + "0 unites in Elantris (next to: Narnia,Midkemia,Scadrial,Roshar)\n"
//          + "0 unites in Mordor (next to: Scadrial,Hogwarts)\n" + "0 unites in Narnia (next to: Elantris,Midkemia)\n"
//          + "0 unites in Hogwarts (next to: Mordor,Scadrial,Roshar)\n" + "Green player :\n" + "--------------\n"
//          + "0 unites in Scadrial (next to: Elantris,Midkemia,Oz,Mordor,Hogwarts,Roshar)\n"
//          + "0 unites in Midkemia (next to: Narnia,Elantris,Scadrial,Oz)\n"
//          + "0 unites in Oz (next to: Midkemia,Scadrial)\n"
//          + "0 unites in Roshar (next to: Elantris,Scadrial,Hogwarts)\n";
//      assertEquals(expected, bytes.toString());
//    } catch (IOException e) {
//      // e.printStackTrace();
//    } catch (InterruptedException r) {
//      // r.printStackTrace();
//    } catch (NullPointerException e) {
//      // e.printStackTrace();
//    } catch (Exception e) {
//      // e.printStackTrace();
//    }
//  }
//
//  private ClientGame createClientGameWithNum(String inputData, OutputStream bytes, int num) throws IOException {
//    BufferedReader input = new BufferedReader(new StringReader(inputData));
//    PrintStream output = new PrintStream(bytes, true);
//    ClientGame game = new ClientGame(input, output);
//    MapFactory factory = new MapFactory();
//    Map m = factory.generateMapWithPlayer(num);
//    game.setMapAndView(m);
//    game.setGameID(101);
//    game.setPlayerID(1);
//    game.getMap().setAssign(generatePlayerList(num));
//    return game;
//  }
//
//  private ArrayList<Player> generatePlayerList(int num) {
//    ArrayList<Player> PL = new ArrayList<>();
//    for (int i = 0; i < num; i++) {
//      PL.add(new HumanPlayer(i + 1));
//    }
//    return PL;
//  }
//
//  private Thread createClient(String inFile) {
//    Thread c = new Thread() {
//      @Override()
//      public void run() {
//        InputStream input = getClass().getClassLoader().getResourceAsStream(inFile);
//        assertNotNull(input);
//
//        InputStream oldIn = System.in;
//
//        try {
//          System.setIn(input);
//          String[] args = { "127.0.0.1", "8888" };
//          ClientApp.main(args);
//        }
//        finally {
//          System.setIn(oldIn);
//          System.setOut(System.out);
//        }
//      }
//    };
//
//    return c;
//  }
//
//  @Disabled
//  @Test
//  void test_showGameResult() {
//    String msg = "Congratulations! you win!\n" + "Game Over. The Winner of game 5000 is player Green.";
//    Thread server = new Thread() {
//        @Override()
//        public void run() {
//          try {
//          Utility u = new Utility();
//          ServerSocket s = new ServerSocket(8890);
//          Socket ss = s.accept();
//          u.sendMsg(ss, msg);
//          s.close();
//          } catch(Exception e) {
//          }
//        }
//    };
//
//    server.start();
//    try {
//      Thread.sleep(100);
//      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//      ClientApp app = new ClientApp("localhost", 8890, new BufferedReader(new StringReader("M\nD\nC\n")),
//          new PrintStream(bytes));
//      app.game = createClientGameWithNum("M\nD\nC\n", bytes, 2);
//      assertNotEquals(null, app.inputReader);
//
//      app.showGameResult();
//      assertEquals(msg, bytes.toString());
//    } catch (Exception e) {
//    }
//  }

}