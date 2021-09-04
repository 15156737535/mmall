$(function(){
    //计算总价
    const array = $(".qprice");
    let totalCost = 0;
    for(let i = 0; i < array.length; i++){
        const val = parseInt($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    //settlement2使用
    $("#settlement2_totalCost").val(totalCost);
});

//商品数量++
function addQuantity(obj){
    const index = $(".car_btn_2").index(obj);
    const stock = parseInt($(".productStock").eq(index).val());
    const price = parseFloat($(".productPrice").eq(index).val());
    const inputObj = $(".car_ipt").eq(index);
    let quantity = inputObj.val();
    const id = $(".id").eq(index).val();
    ++quantity;
    if(quantity > stock){
        quantity = stock;
        alert("库存不足！");
    }
    const cost = quantity * price;
    $.ajax({
        url:"/cart/updateCart/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        dataType:"text",
        success:function (data) {
            if(data == "success"){
                let totalCost;
//更新toSettlement的数据
                $(".qprice").eq(index).html("￥"+cost);
                inputObj.val(quantity);
                if(quantity < stock){
                    totalCost = parseInt($("#totalprice").html().substring(1));
                    totalCost += price;
                    $("#totalprice").html("￥"+totalCost);
                }
                //更新searchBar的数据
                $(".quantity").eq(index).text(quantity);
                $(".cost").eq(index).text(cost);

                const array = $(".cost");
                totalCost = 0;
                for(let i = 0; i < array.length; i++){
                    const val = parseInt($(".cost").eq(i).html());
                    totalCost += val;
                }
                $("#totalCost").html("￥"+totalCost);
            }
        }
    });
}

//商品数量--
function subQuantity(obj){
    const index = $(".car_btn_1").index(obj);
    const price = parseInt($(".productPrice").eq(index).val());
    const inputObj = $(".car_ipt").eq(index);
    let quantity = inputObj.val();
    const id = $(".id").eq(index).val();
    --quantity;
    if(quantity == 0){

        quantity = 1;
    }
    const cost = quantity * price;
    $.ajax({
        url:"/cart/updateCart/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        dataType:"text",
        success:function(data){
            if(data == "success"){
                $(".qprice").eq(index).html("￥"+cost);
                inputObj.val(quantity);
                if(quantity!=1){
                    var totalCost = parseInt($("#totalprice").html().substring(1));
                    totalCost -= price;
                    $("#totalprice").html("￥"+totalCost);
                }
                $(".quantity").eq(index).text(quantity);
                $(".cost").eq(index).text(cost);

                var array = $(".cost");
                var totalCost = 0;
                for(var i = 0;i < array.length;i++){
                    var val = parseInt($(".cost").eq(i).html());
                    totalCost += val;
                }
                $("#totalCost").html("￥"+totalCost);
            }
        }
    });
}

//移出购物车
function removeCart(obj){
    const index = $(".delete").index(obj);
    const id = $(".id").eq(index).val();
    if(confirm("是否确定要删除？")){
        window.location.href = "/cart/deleteById/"+id;
    }
}