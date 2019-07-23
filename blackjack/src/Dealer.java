import java.util.ArrayList;

public class Dealer extends Player {
    Dealer(){
        score = 0;
        hand = new ArrayList<Card>();
        hand.clear();
        aces = 0;
    }

    @Override
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
    }

    public void botBehavior(Deck d,Player p){
        if (score<17&&score<=p.score){
            System.out.print("Dealer ");
            hits(d);
        }
        if (score==17 && aces>0 && score<=p.score) {
            System.out.print("Dealer ");
            hits(d);
        }
    }

    public void print1st(){
        System.out.println("Dealer's 1st card:" + hand.get(0).rank + " of " + hand.get(0).suit);
    }
    public void print2nd(){
        System.out.println("Dealer's 2nd card:" + hand.get(1).rank + " of " + hand.get(1).suit);
        System.out.println("Dealer total: " + score);
    }
    public void loses(Deck d){
        pReset(d);
        System.out.println("Dealer bust!");
    }
}

