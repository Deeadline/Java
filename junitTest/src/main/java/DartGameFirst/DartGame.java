package DartGameFirst;

public class DartGame {
    private int firstShooter = 501;
    private int secondShooter = 501;

    public void shoot(int value, int multiplicity, int playerTurn) {
        validate(value,multiplicity);
        if(playerTurn == 1){
            firstShooter = countPoints(value, multiplicity, getFirstPoints());
            if(checkWin(firstShooter)){
                System.out.println("FIRST PLAYER WON");
            }
        }
        else {
            secondShooter = countPoints(value, multiplicity, getSecondPoints());
            if(checkWin(secondShooter)){
                System.out.println("SECOND PLAYER WON");
            }
        }
    }
    private int countPoints(int value, int multiplicity, int points){
        points -= value*multiplicity;
            if(points == 0){
                return 1000;
            }
            else if (points < 0){
                return points+value*multiplicity;
            }
           return points;
    }
    private boolean checkWin(int points){
        if(points == 1000){
            return true;
        }
            return false;
    }
    public void validate(int value, int multiplicity) throws IllegalArgumentException{
        if(value > 20 && value != 50 && value !=25){
            throw new IllegalArgumentException("Value is invalid");
        }
        if(multiplicity != 2 && multiplicity != 1 && multiplicity != 3){
            throw  new IllegalArgumentException("Multiplicity is invalid");
        }
    }
    public int getFirstPoints() {
        return firstShooter;
    }

    public int getSecondPoints() {
        return secondShooter;
    }

    public void setFirstShooter(Integer firstShooter) {
        this.firstShooter = firstShooter;
    }

    public void setSecondShooter(Integer secondShooter) {
        this.secondShooter = secondShooter;
    }
}
