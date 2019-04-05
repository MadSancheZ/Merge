import model.Price;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public List<Price> unitePrices(List<Price> oldPrices, List<Price> newPrices){
        if(oldPrices == null || oldPrices.isEmpty()) return newPrices;
        else {
            List<Price> ans = new ArrayList<>();
            Date newStart;
            Date newEnd;
            for (int i=0; i<Math.max(oldPrices.size(), newPrices.size()); i++) {
                Price oldPrice = oldPrices.get(i);
                Price newPrice = newPrices.get(i);
                Price curPrice;
                if (!isSamePrice(oldPrice, newPrice)){
                    if(oldPrice.getBegin().before(newPrice.getBegin())) {
                        curPrice = oldPrice;
                        curPrice.setEnd(newPrice.getBegin());
                        ans.add(curPrice);
                        curPrice = newPrice;
                        if(oldPrice.getEnd().before(newPrice.getEnd())) {
                            ans.add(curPrice);
                        }else {
                            ans.add(curPrice);
                            curPrice = oldPrice;
                            curPrice.setBegin(newPrice.getEnd());
                            ans.add(curPrice);
                        }
                    } else {
                        curPrice = newPrice;
                        ans.add(curPrice);
                        if(newPrice.getEnd().before(oldPrice.getEnd())){
                            curPrice = oldPrice;
                            curPrice.setBegin(newPrice.getEnd());
                            ans.add(curPrice);
                        }
                    }
                }else {
                    curPrice = oldPrice;
                    newStart = oldPrice.getBegin().before(newPrice.getBegin()) ? oldPrice.getBegin() : newPrice.getBegin();
                    newEnd = oldPrice.getEnd().before(newPrice.getEnd()) ? newPrice.getEnd() : oldPrice.getEnd();
                    curPrice.setBegin(newStart);
                    curPrice.setEnd(newEnd);
                    ans.add(curPrice);
                }
            }
            return ans;
        }
    }

    private boolean isSamePrice(Price oldPrice, Price newPrice){
        return oldPrice.getValue() == newPrice.getValue() && oldPrice.getNumber() == newPrice.getNumber();
    }

    public static void main(String[] args) {
        Price one = null;
        Price two = null;
        Price three = null;
        Price four = null;
        Price five = null;
        Price six = null;
        try {
            one = new Price(1, "122856", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 23:59:59"), 11000);
            two = new Price(2, "122856", 2, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("10.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 23:59:59"), 99000);
            three = new Price(1, "6654", 1, 2, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("01.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("31.01.2013 00:00:00"), 5000);
            four = new Price(1, "122856", 1, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("20.02.2013 23:59:59"), 11000);
            five = new Price(2, "122856", 2, 1, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("15.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("25.01.2013 23:59:59"), 92000);
            six = new Price(2, "6654", 1, 2, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("12.01.2013 00:00:00"), new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("13.01.2013 00:00:00"), 4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Price> old = new ArrayList<Price>();
        old.add(one);
        old.add(two);
        old.add(three);
        List<Price> newPr = new ArrayList<Price>();
        newPr.add(four);
        newPr.add(five);
        newPr.add(six);
        new Main().unitePrices(old, newPr).forEach(System.out::println);
    }
}
