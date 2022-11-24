[#ftl]
[@c.page]
    <div class="detail">
        <div class="vizitka">
            <div class="jmeno">${vizitka.celeJmeno}</div>
            <div class="firma">${vizitka.firma}</div>
            <div class="udaje">
                <div class="adresa">
                    <div>${vizitka.ulice}</div>
                    <div>${vizitka.psc}</div>
                </div>
                <div class="kontakty">
                    [#if vizitka.email?exists]
                        <div><i class="las la-at"></i> <span>${vizitka.email}</span></div>
                    [/#if]
                    [#if vizitka.telefon?exists]
                        <div><i class="las la-phone-alt"></i> <span>${vizitka.telefon}</span></div>
                    [/#if]
                    [#if vizitka.web?exists]
                        <div><i class="las la-globe"></i> <span>${vizitka.web}</span></div>
                    [/#if]
                </div>
            </div>
        </div>

        <div class="mapa">
            <iframe style="border:none" src="https://frame.mapy.cz/?q=${vizitka.celaAdresa?url}" width="100%" height="100%" frameborder="0"></iframe>
        </div>

        <nav class="navbar">
            <form action="/delete" method="post">
                <input type="hidden" name="id" value="${vizitka.id}" />
                <button type="submit" ><b>Smazat</b></button>
            </form>
        </nav>
             [@c.back /]
        </div>
    </div>
[/@c.page]
