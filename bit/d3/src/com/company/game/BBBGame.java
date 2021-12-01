package com.company.game;

public class BBBGame implements Game{

    private int count;

    @Override
    public String play(String input) throws Exception {
        ++count;

        if (count % 5 == 0) {
            return "당첨";
        }
        return "꽝";
    }
}
