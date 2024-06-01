function appendSidebar() {

    let container = $('body .container');

    let txt1 = `<div class="container container-main">
                            <div class="container side-bar">
                                <ul>
                                    <li><a href="#">Главная</a></li>
                                    <li>
                                        <ul>
                                            <li>ПУБЛИЧНЫЕ</li>
                                            <li><a href="#">Вопросы</a></li>
                                            <li><a href="#">Метки</a></li>
                                            <li><a href="#">Участники</a></li>
                                            <li><a href="#">Неотвеченные</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>`
    $('body').prepend(txt1);
    $('.container-main').append(container);

    $('.side-bar').css({
        "width": "164px",
        "float": "left",
        "padding": "20px 0 0 0",
        "color": "grey",
        "font-size": "10pt"
    })
    $('.side-bar a').css({
        "color": "grey"
    })
    $('.container-main .container:last-child').css({
        "padding-left": "180px"
    })
    $('.side-bar ul').css({
        "list-style-type": "none"
    })
    $('.side-bar ul li').css({
        "padding": "5px 5px"
    })
    $('.side-bar ul ul').css({
        "margin-left": "-28px"
    })
    $('.side-bar ul ul li:first-child').css({
        "margin-left": "-10px"
    })
}
appendSidebar();
