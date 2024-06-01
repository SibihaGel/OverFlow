
function appendHeader() {
    let txt1 = `<header>
                            <div class="container">
                                <div class="row">
                                    <div class="col-2">
                                        <a href="#" class="logo"><img src="../static/images/header/pngwing.com.png"></a>
                                    </div>
                                    <div class="col-8">
                                        <form>
                                            <input class="search-input" type="text" placeholder="Поиск..">
                                        </form>
                                    </div>
                                    <div class="col">
                                        <div class="profileButton"><a href="#">My profile</a></div>
                                    </div>
                                </div>
                            </div>
                    </header>`
    $('body').prepend(txt1);
    $('header').css({
        "height": "52px",
        "border-top": "orange 3px solid",
    "border-bottom": "lightgray 1px solid"
    })
    $('.search-input').css({
        "width":" 100%",
        "border-radius": "5px",
        "margin": "10px 0"
    })
    $('.profileButton').css({
        "padding":" 5px",
        "border": "1px solid deepskyblue",
        "border-radius": "5px",
        "margin":" 5px",
        "text-align": "center",
        "width":" 50%"
    })


    $(".logo img").mouseover(function() {
        $(this).css("background-color","lightgray");
    }).mouseout(function() {
        $(this).css("background-color","transparent");
    });

}
appendHeader();

