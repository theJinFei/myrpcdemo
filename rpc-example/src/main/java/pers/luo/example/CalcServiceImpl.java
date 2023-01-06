package pers.luo.example;

public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        return x - y;
    }
}
