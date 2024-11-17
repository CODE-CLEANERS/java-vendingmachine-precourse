package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;

public class RandomCoinGenerator {
    private RandomCoinGenerator() {
        throw new UnsupportedOperationException();
    }

    public static int getRandomChangeAmount(){
        ArrayList<Integer> numberRanges = new ArrayList<>();
        numberRanges.add(450);
        numberRanges.add(550);
        numberRanges.add(650); // 이걸 내가 해야한다고라...
        return Randoms.pickNumberInList(numberRanges);
    }
}
