import java.util.ArrayList;
import java.util.List;

/*
A라는 전자 제품 매장이 오픈되면
[클라이언트 요구사항]
가전제품은 제품의 가격, 제품의 포인트 정보를 공통적으로 가지고 있다.
각각의 가전제품은 제품별 고유한 이름을 가지고 있다.

ex)
각각의 전자제품은 이름을 가지고 있다.(Tv, Audio, Computer)
각각의 전자 제품은 다른 가격을 가지고 있다.(Tv:5000, Audio: 6000...)
제품의 포인트는 가격의 10% 적용한다.

시뮬레이션 시나리오
구매자: 제품을 구매하기 위한 금액정보, 포인트 젖ㅇ보를 가지고 있다.
예를 들면: 10만원, 포인트 0
구매자는 제품을 구매할 수 있다. 구매행위를 하게 되면 가지고 있는 돈은 감소하고 포인트는 올라간다.
구매자는 처음 초기 금액을 가질 수 있다.

요구사항
카트(Cart)
카트에는 매장에 있는 모든 전자 제품을 담을 수 있다.
카트의 크기는 고정되어 있다.(10개)

계산대에 가면 전체 계산
계산기능이 필요
summary() 기능 추가
당신이 구매한 물건 이름과 가격정보 나열
총 누적 금액, 계산 출력

hint) 카트 물건을 담는 행위 (Buy() 함수 안에 cart 담는 것을 구현)
hint) Buyer... >> summary() main 함수에서 계산할 때

구매자는 default 금액을 가지고 있고 초기금액을 설정할 수 있다.

 */
class Product {
    int price;
    int bonuspoint;

    Product(int price) {
        this.price = price;
        this.bonuspoint = (int) (this.price / 10.0);
    }
}

class KtTv extends Product {
    //가격정보 부모
    KtTv() {
        super(1);
    }

    @Override
    public String toString() {
        return "KtTv";
    }
}


class Audio extends Product {
    //가격정보 부모
    Audio() {
        super(100);
    }

    @Override
    public String toString() {
        return "Audio";
    }
}

class NoteBook extends Product {
    //가격정보 부모
    NoteBook() {
        super(150);
    }

    @Override
    public String toString() {
        return "NoteBook";
    }
}

/*
하와이 휴가

공식오픈
매장에 1000개의 제품이 추가 (POS 구축) 자동 등록
매장에 1000개의 제품이 전시

고객 >> 구매 >> 3개 >> 997개의 판매 불가

전화 >>  욕(...) >> 친구 >> PC >> 접속 >> 997개의 함수 >> 휴가 ...비행기 .....

하와이 즐거운 휴가
1. 모든 제품은 Product 상속
2. 함수의 통합
3. 한개의 함수가 모든 제품의 판매 담당
4. 다형성
*/

class Buyer {
    int money = 1000;
    int bonuspoint;

    //구매자는 구매 행위를 할 수 있다 (기능)
    //구매행위(잔액 - 제품의 가격 , 포인트 정보 갱신(증가)
    //*******구매자는 매장에 있는 모든 물건을 구매할 수 있다 ***********

    //카트
    List<Product> cart; // 10칸 짜리

    public Buyer() {
        cart = new ArrayList<>();
    }

    void buyProduct(Product p) {
        if (this.money < p.price) {
            System.out.println("잔액이 부족합니다. 현재 잔액: " + this.money);
            return;
        }

        if (cart.size() < 10) {
            this.money -= p.price;
            this.bonuspoint += p.bonuspoint;
            System.out.println("구매한 물건은 :" + p.toString());
            System.out.println("현재 잔액: " + this.money + " 보너스포인트: " + this.bonuspoint);
            cart.add(p);
            return;
        }

        System.out.println("카드가 꽉 찼습니다.");
    }

    void summary() {
        int idx = 1;
        int totalPrice = 0;
        for (Product product : cart) {
            System.out.println(idx++ + "번 제품명 " + product.toString() + " 제품 가격: " + product.price);
            totalPrice += product.price;
        }
        System.out.println("누적금액: " + totalPrice);
    }
}    //목록출력 ...상품리스트

//냉장고 판매 함수
//드라이기 판매 함수 ..

public class Ex01_Product {
    public static void main(String[] args) {
        Buyer buyer = new Buyer();
        NoteBook noteBook = new NoteBook();
        Audio audio = new Audio();
        KtTv ktTv = new KtTv();
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.buyProduct(ktTv);
        buyer.summary();

    }

}
