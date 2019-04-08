import model.Price;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public List<Price> mergePrices(List<Price> oldPrices, List<Price> newPrices) throws CloneNotSupportedException {
        if(oldPrices == null || oldPrices.isEmpty()) return newPrices;
        if(newPrices == null || newPrices.isEmpty()) return oldPrices;
        List<Price> ans = new ArrayList<>();
        Date newStart;
        Date newEnd;
        int i = 0;
        int j = 0;
        while (i < oldPrices.size() && j < newPrices.size()) {
            Price oldPrice = oldPrices.get(i);
            Price newPrice = newPrices.get(j);
            Price curPrice;
            if(isSamePriceAndProduct(oldPrices.get(i), newPrices.get(j))){
                curPrice = (Price) oldPrice.clone();
                newStart = oldPrice.getBegin().before(newPrice.getBegin()) ? oldPrice.getBegin() : newPrice.getBegin();
                newEnd = oldPrice.getEnd().before(newPrice.getEnd()) ? newPrice.getEnd() : oldPrice.getEnd();
                curPrice.setBegin(newStart);
                curPrice.setEnd(newEnd);
                ans.add(curPrice);
                i++;
                j++;
            }else if(isSameProduct(oldPrices.get(i), newPrices.get(j))){
                if(oldPrice.getBegin().before(newPrice.getBegin())){
                    curPrice = (Price) oldPrice.clone();
                    curPrice.setEnd(newPrice.getBegin());
                    ans.add(curPrice);
                    if(oldPrice.getEnd().after(newPrice.getEnd())) {

                        ans.add(newPrice);
                        curPrice = (Price) oldPrice.clone();
                        curPrice.setBegin(newPrice.getEnd());
                        ans.add(curPrice);
                        j++;
                    }else{
                        curPrice = (Price) newPrice.clone();
                        ans.add(curPrice);
                        i++;
                    }
                }else{
                    curPrice = (Price) newPrice.clone();
                    ans.add(curPrice);
                    if(oldPrice.getEnd().before(newPrice.getEnd())){
                        i++;
                    }else{
                        curPrice = (Price) oldPrice.clone();
                        curPrice.setBegin(newPrice.getEnd());
                        ans.add(curPrice);
                        i++;
                        j++;
                    }
                }
            }else {
                if(i < j) i++;
                if(i > j) j++;
                else{
                    ans.add(oldPrice);
                    ans.add(newPrice);
                    i++;
                    j++;
                }
            }

        }
        return ans;
    }

    private boolean isSamePriceAndProduct(Price oldPrice, Price newPrice){
        return oldPrice.getValue() == newPrice.getValue() && oldPrice.getNumber() == newPrice.getNumber();
    }

    private boolean isSameProduct(Price oldPrice, Price newPrice){
        return oldPrice.getNumber() == newPrice.getNumber();
    }

    public static void main(String[] args){

    }
}
