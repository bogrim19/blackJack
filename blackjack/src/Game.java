import javax.swing.*;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Deck d = new Deck();
        Player p = new Player();
        Player p2 = new Player();
        Dealer bot = new Dealer();
        String c=" ";
        String splitter;

        while (p.balance >= 0) {
            System.out.println("Game started!");
            p.placeBet();
            p.recieveHand(d);
            p.printHand();
            bot.recieveHand(d);
            bot.print1st();
            Scanner scan = new Scanner(System.in);

            for (int i = 0; i < 4; i++) {
                System.out.println("hit/ double/ stand/ split");
                c = scan.nextLine();

                switch (c) {
                    case "stand":
                        break;
                    case "hit": {
                        p.hits(d);
                        break;
                    }
                    case "double": {
                        p.bet = p.bet * 2;
                        p.hits(d);
                        break;
                    }
                    case "split": {
                        p2.balance = new Long(0);
                        p2.bet = p.bet;
                        p.balance -= p.bet;

                        p2.hand.add(p.hand.get(1));
                        p2.score+= p.hand.get(1).getScore();
                        p.score-= p.hand.get(1).getScore();
                        p.hand.remove(1);

                        System.out.println("1st Hand: ");
                        System.out.println(p.hand.get(0).rank + " of " + p.hand.get(0).suit);
                        p.hits(d);
                        System.out.println("2nd Hand: ");
                        System.out.println(p2.hand.get(0).rank + " of " + p2.hand.get(0).suit);
                        p2.hits(d);

                        System.out.println("1st Hand: hit/double/stand");
                        do {
                            splitter = scan.nextLine();
                            switch (splitter) {
                                case "hit": {
                                    p.hits(d);
                                    break;
                                }
                                case "double": {
                                    p.bet = p.bet * 2;
                                    p.hits(d);
                                    break;
                                }
                                case "stand": {
                                    break;
                                }
                                default:
                                    System.out.println("Invalid command");
                            }
                        } while (splitter.equals("hit")&&p.score!=0);

                        System.out.println("2nd Hand: hit/double/stand");
                        do {
                            splitter = scan.nextLine();
                            switch (splitter) {
                                case "hit": {
                                    p2.hits(d);
                                    break;
                                }
                                case "double": {
                                    p2.bet = p2.bet * 2;
                                    p2.hits(d);
                                    break;
                                }
                                case "stand": {
                                    break;
                                }
                                default:
                                    System.out.println("Invalid command");
                            }
                        } while (splitter.equals("hit")&&p2.score!=0);

                    break;
                    }
                    default:
                        System.out.println("Invalid command");
                }
                if (c.equals("stand") || c.equals("double") || c.equals("split")) break;
                if (p.score == 0) break;
            }

            bot.print2nd();
            while (bot.score != 0 && bot.score < p.score) bot.botBehavior(d, p);


            EvaluateRound(p,bot,d,c);

            if (p2.bet != 0) {
                EvaluateRound(p2,bot,d,c);
                p.setBalance(p.getBalance()+p2.getBalance());
                p2.setBalance(new Long(0));
            }

            if (p.balance == 0) {
                p.gameOver();
                break;
            }
        }
    }

    static void  EvaluateRound(Player p, Dealer bot, Deck d, String c){
        if (p.score != 0) {
            if (p.score == bot.score) {
                if (c.equals("double")) p.bet = p.bet * 2;
                p.draws(d);
                bot.pReset(d);
            }

            if (p.score > bot.score) {
                p.wins(d);
                bot.pReset(d);
            }

            if (p.score < bot.score) {
                p.loses(d);
                bot.pReset(d);
            }
        }
    }
}
