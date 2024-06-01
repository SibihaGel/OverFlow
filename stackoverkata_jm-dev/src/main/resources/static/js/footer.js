function appendFooter() {
    let txt1 = `<footer>
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <h5>STACKOVERKATA</a></h5>
                                    <ul class="footer-list list-group">
                                        <li>Тур</li>
                                        <li>Справка</li>
                                        <li>Чат</li>
                                        <li>Связаться с нами</li>
                                        <li>Оставить отзыв</li>
                                    </ul>
                    
                                </div>
                                <div class="col">
                                    <h5>КОМПАНИЯ</a></h5>
                                    <ul class="footer-list list-group">
                                        <li>Stackoverkata</li>
                                        <li>Teams</li>
                                        <li>Advertising</li>
                                        <li>О компании</li>
                                    </ul>
                    
                                </div>
                                <div class="col">
                                    <h5>СЕТЬ STACKEXCHANGE</a></h5>
                                    <ul class="footer-list list-group">
                                        <li>Технологии</li>
                                        <li>Культура и отдых</li>
                                        <li>Наука</li>
                                        <li>Специализация</li>
                                        <li>Бизнес</li>
                                        <li>Данные</li>
                                        <li>API</li>
                                    </ul>
                    
                                </div>
                                <div class="col">
                                    <div class="footer-social">
                                        <a href="">Блог</a>
                                        <a href="">Facebook</a>
                                        <a href="">Twitter</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </footer>`
    $('body').append(txt1);
    $('footer').css({
        "margin": "20px 0 0 0",
        "padding": "15px 0px",
        "background-color": "rgb(35, 38, 41)",
        "height": "397px",
        "color": "rgb(159, 166, 173)",
    })
    $('footer h5').css({
        "color": "rgb(186, 191, 196)",
        "font-weight": "bold"
    })
    $('.footer-list').css({
        "list-style-type": "none",
        "font-size": "10pt"
    })
    $('.footer-list li').css({
        "padding": "5px 0px"
    })
    $('.footer-social a').css({
        "padding": "0 10px",
        "font-size": "8pt",
        "color": "rgb(159, 166, 173)"
    })
}
appendFooter();