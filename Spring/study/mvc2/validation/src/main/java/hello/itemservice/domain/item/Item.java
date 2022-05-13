package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10000원 넘게 입력해주세요.")
//@ScriptAssert를 적용하면 오브젝트 오류를 검증해준다.
//하지만 제약조건이 너무 많기 때문에 직접 자바 코드로 작성하는 것을 권장한다.
public class Item {

//    @NotNull(groups = UpdateCheck.class)
// id값은 상품을 수정할 경우에만 필요하다
// 상품을 처음 저장할 경우 id 값이 존재하지 않으니
// @NotNull을 UpdateCheck라는 그룹에 넣어서 상품 수정시에만 적용되도록 한다.
// Group을 사용하려면 @Validated를 사용해야한다.
    private Long id;

//    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999, groups = {SaveCheck.class})
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
