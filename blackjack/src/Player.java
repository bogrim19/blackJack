

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    short score;
    Long balance, bet;
    ArrayList<Card> hand;
    boolean canSplit;
    short aces;


    public Player() {
        score = 0;
        hand = new ArrayList<Card>();
        hand.clear();
        balance = new Long(1000);
        bet = new Long (0);
        canSplit = false;
        aces = 0;
    }

    public void pReset(Deck d) {
        score = 0;
        d.putBack(hand);
        hand.clear();
        bet = new Long(0);
        canSplit = false;
        aces = 0;
    }

    public void placeBet(){ //TODO
        if(balance.equals(0)) {
            System.out.println("You're broke!");
            gameOver();
        }


        System.out.println("Your balance is: " + balance );
        System.out.println("Place your bets: ");
        Scanner in = new Scanner(System.in);
        bet = in.nextLong();

        if (bet > balance) { bet = new Long(0);
            System.out.println("Oops! It seems like you're trying to bet more than you have!");
            System.out.println("Would you like to exchange your house keys?");
            System.out.println("HAH! Kidding! Just bet less!");
            placeBet();
        }
        else {
            balance = balance - bet;
        }
    }

    public Long addToBalance (Long wonAmount){
        return balance+=wonAmount;
    }

    public void recieveHand(Deck d){
        Card c1 = (d.pullCard());
        hand.add(c1);
        if (c1.rank== Card.Ranks.ACE) aces++;
        score+=c1.getScore();

        Card c2 = (d.pullCard());
        hand.add(c2);
        score+=c2.getScore();
        if (c2.rank== Card.Ranks.ACE) aces++;

        if(c1.rank==c2.rank) canSplit = true;
        if(aces == 2) score-=10;
        if(c1.getScore()+c2.getScore()==21) winsBJ(d);


    }

    public void winsBJ(Deck d){ // BlackJack wins pay x1.25
        balance+=2*bet+bet/2;
        pReset(d);
    }

    public void hits(Deck d){
        Card c = (d.pullCard());
        hand.add(c);
        System.out.println("Hit card: " + c.rank + " of " + c.suit);
        if (c.rank== Card.Ranks.ACE) aces++;
        score+=c.getScore();
        if(aces>0&&score>21){
            aces--;
            score-=10;
        }
        System.out.println("Total: " + score);
        if(score>21) loses(d);
    }

    public void wins(Deck d){
        balance+=2*bet;
        pReset(d);
        System.out.println("Round won!");
    }

    public void draws(Deck d){
        balance+=bet;
        pReset(d);
        System.out.println("Round draw!");
    }

    public void loses(Deck d){
        pReset(d);
        System.out.println("Round lost!");
    }

    public void gameOver(){
        System.out.println("GAME OVER");
    }

    public void printHand(){
        System.out.println(hand.get(0).rank + " of " + hand.get(0).suit);
        System.out.println(hand.get(1).rank + " of " + hand.get(1).suit);
        System.out.println("Total: "+score);
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
