package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final List<Number> numbers = new ArrayList<>();
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_SUBLIST_INDEX = 0;
    private static final int MAX_SUBLIST_INDEX = LottoTicket.LOTTO_NUMBER_COUNT;
    private static final int MINUS_PURCHASE_COUNT = 1;

    public LottoGenerator() {
        initNumbers();
    }

    private void initNumbers() {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(new Number(i));
        }
    }

    public LottoTickets createLottoTickets(PurchaseCount purchaseCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (purchaseCount.isGreaterThanZero()) {
            Collections.shuffle(numbers);
            List<Number> numbers = new ArrayList<>(LottoGenerator.numbers.subList(MIN_SUBLIST_INDEX, MAX_SUBLIST_INDEX));
            Collections.sort(numbers);
            lottoTickets.add(new LottoTicket(numbers));
            purchaseCount = purchaseCount.minus(MINUS_PURCHASE_COUNT);
        }
        return new LottoTickets(lottoTickets);
    }
}