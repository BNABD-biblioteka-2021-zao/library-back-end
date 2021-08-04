package pl.weeia.library;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.weeia.library.model.entities.Book;
import pl.weeia.library.model.entities.BookCopy;
import pl.weeia.library.model.entities.LibraryUser;
import pl.weeia.library.repositories.BookCopyRepository;
import pl.weeia.library.repositories.BookRepository;
import pl.weeia.library.repositories.BorrowingRepository;
import pl.weeia.library.repositories.LibraryUserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class SampleDataProvider implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final LibraryUserRepository userRepo;
    private final BookRepository bookRepo;
    private final BookCopyRepository copyRepository;
    private final BorrowingRepository borrowingRepo;

    @Override
    public void run(ApplicationArguments args) {
        List<LibraryUser> users = userRepo.saveAll(Arrays.asList(
                new LibraryUser("test", passwordEncoder.encode("password"), "email@email.email", "ROLE_USER"),
                new LibraryUser("librarian", passwordEncoder.encode("password"), "librarian@email.lib", "ROLE_LIBRARIAN")
        ));
        List<Book> books = bookRepo.saveAll(Arrays.asList(
                new Book("Pan Tadeusz", "Epopeja", "Adam Mickiewicz", "Some desc"),
                new Book("Zemsta", "Komedia", "Aleksander Fredro", "wać Panie"),
                new Book("Billy Summers", "Horror", "King Stephen", "Billy Summers jest najlepszy w swoim fachu. Eliminuje ludzi, ale tylko tych naprawdę złych. Był snajperem w Iraku, więc zna się na rzeczy i zawsze strzela celnie. Tym razem przyjmuje ostatnie zlecenie. Czas w końcu na zasłużoną emeryturę. Niestety, coś idzie nie tak… A nawet wszystko."),
                new Book("Gra Geralda", "Horror", "King Stephen", "Niektórzy zarzucają Stephenowi Kingowi, że nie potrafi pisać o kobietach. W Grze Geralda nie po raz pierwszy Mistrz Horroru udowadnia, że się mylą. Pisze o kobiecie postawionej w sytuacji ekstremalnej. I jak on to robi! \n" +
                        "Gerald Burlingham, chcąc dodać trochę pieprzu do małżeńskiego seksu, zabiera swoją żonę Jessie na odludzie w Maine. Kiedy Jessie przestaje się podobać brutalna zabawa, postanawia ją przerwać. A wtedy domek letniskowy staje się sceną grozy. \n" +
                        "Przykuta kajdankami do łóżka kobieta zostaje sama.\n" +
                        "Nie licząc trupa męża.\n" +
                        "Tego, co czyha w mroku.\n" +
                        "I tego, co czai się w niej samej."),
                new Book("To", "Horror", "King Stephen", "Najbardziej przerażająca powieść króla grozy. Doceniona przez miliony czytelników na całym świecie. Otoczona kultowym uwielbieniem.\n" +
                        "\n" +
                        "Opublikowana w 1986 roku powieść straszy kolejne pokolenia czytelników i niezmiennie znajduje się w czołówce zestawień najbardziej przerażających horrorów wszech czasów.\n" +
                        "\n" +
                        "Wydanie w nowej okładce filmowej!\n" +
                        "\n" +
                        "Derry w stanie Maine, miejsce ledwie widoczne na mapie. Dochodzi tu do wyjątkowej eskalacji zbrodni, okrutnych morderstw, gwałtów i tajemniczych wypadków. W kanałach miasteczka zaległo się To. Bliżej nieokreślone, przybiera najróżniejsze postacie – klauna, ogromnego ptaszyska, głosu w rurach. Poluje na dzieci. I tylko dzieci potrafią dostrzec To. Dlatego właśnie one stają do walki z potworem.\n" +
                        "\n" +
                        "Mija dwadzieścia kilka lat i To powraca. Dzieci są już dorosłymi, ale muszą odnaleźć w sobie dziecięcą wiarę, lojalność i odwagę, by skutecznie stawić mu czoła.\n" +
                        "\n" +
                        "A czy ty odważysz się sięgnąć po balonik...?"),
                new Book("Wyspa", "Obyczajowe", "Hislop Victoria", "ramatyczna opowieść o czterech pokoleniach rodziny rozdzielonej przez wojnę i chorobę. O zakazanej miłości i trudnych wyborach, których musi dokonywać każda z nas. Jedna z najchętniej kupowanych książek XX wieku! Autorkę wyróżniono British Book Award za debiut literacki, a jej powieść \"Wyspa\" była mocną kandydatką do tytułu brytyjskiej książki roku."),
                new Book("Najemnika śmierć nie tyka. Deadpool. Tom 1", "Komiks", "Young Skottie", "Pierwszy tom przygód Deadpoola z nowej serii wydawniczej Marvel Fresh! Po tym, jak Wade Wilson srodze rozczarował się życiem superbohatera, wykasował sobie pamięć i wrócił do tego, w czym jest najlepszy – najemnikowania! Niestety doskwiera mu brak zleceń, a co za tym idzie – gotówki, wpada więc na pomysł, by zaaranżować własny komiksowy event. Problem w tym, że Avengers nie są zbyt chętni do współpracy. Poszukując łatwiejszych okazji do zarobku, Deadpool trafia do Dziwoświata, odpiera hordy zombie w galerii handlowej i… cierpi na straszną chandrę. Na szczęście w pobliżu są zabójcy gotowi szybko go z niej wyleczyć!\n" +
                        "\n" +
                        "Autorem tej opowieści jest Skottie Young („Nienawidzę Baśniowa”), a rysunki stworzyli Scott Hepburn i Nic Klein („Thor”). Album zawiera zeszyty #1–6 serii „Deadpool”."),
                new Book("Deadpool leci Szekspirem. Deadpool. Tom 7", "Komiks", "Doescher Ian", "Siódmy tom przygód Deadpoola z serii wydawniczej Marvel NOW! 2.0. Wade Wilson utknął w sztukach Williama Szekspira i choćby nawet oddał królestwo za konia, nie może się wydostać! Czy będzie z tego sen nocy letniej, czy raczej wiele hałasu o nic? Jedno jest pewne – wszystko dobre, co się dobrze kończy, i prędzej czy później sir Deadpool powróci do tragedii, w którą zmieniło się jego prawdziwe życie. Tam zaś czeka na niego pewna demoniczna złośnica, którą nie tak łacno poskromić!\n" +
                        "\n" +
                        "Scenariusz tego tomu napisali Ian Doescher („William Shakespeare’s Star Wars”) i Gerry Duggan („Hawkeye kontra Deadpool”, „Uncanny Avengers”), a rysunki stworzyli: Salva Espin, Scott Hepburn, Sean Izaakse i Bruno Oliveira."),
                new Book("Diuna. Kroniki Diuny Tom 1", "Science Fiction", "Herbert Frank", "Arrakis, zwana Diuną, to jedyne we wszechświecie źródło melanżu. Z rozkazu Padyszacha Imperatora planetę przejmują Atrydzi, zaciekli wrogowie władających nią dotychczas Harkonnenów. Zwycięstwo księcia Leto Atrydy jest jednak pozorne – przejęcie planety ukartowano. W odpowiedzi na atak Imperium i Harkonnenów dziedzic rodu Atrydów Paul staje na czele rdzennych mieszkańców Diuny i sięga po imperialny tron. Oszałamiające połączenie przygody i mistycyzmu, ekologii i polityki."),
                new Book("Mesjasz Diuny. Kroniki Diuny tom 2", "Science Fiction", "Herbert Frank", "Minęło kilkanaście lat, odkąd Fremeni pod dowództwem Paula Muad’Diba pokonali połączone siły Harkonnenów i imperialnych sardaukarów. Paul poślubił księżniczkę Irulanę i zasiadł na tronie Imperium. Pustynna Arrakis, zwana Diuną, jest stolicą wszechświata, a Imperator Paul Atryda wydaje z niej poprzedzone proroczymi wizjami rozkazy. Tymczasem stare ośrodki władzy - Bene Gesserit, Gildia Kosmiczna i Bene Tleilax - zawiązują spisek przeciw nowemu Imperatorowi.\n" +
                        "\n" +
                        "Czczony niczym bóg, Paul Muad’Dib wpada w pułapkę, jaką zastawiła na niego prorocza moc: zna dokładnie każdą chwilę swojej przyszłości, każdy swój ruch, każdą decyzję i - przede wszystkim - swój straszliwy finał..."),
                new Book("Dzieci Diuny. Kroniki Diuny. Tom 3", "Science Fiction", "Herbert Frank", "Upłynęło dziewięć lat, odkąd oślepiony Muad’Dib ruszył samotnie na pustynię na spotkanie z Szej-huludem. W tym czasie Imperium rządziła jego siostra Alia. Na drodze opętanej regentce wciąż jednak stoją prawowici następcy Paula – Leto i Ganima. Dzieci są też zagrożeniem dla rodu Corrinów, odwiecznego wroga Atrydów, chcącego ponownie zasiąść na Tronie Złotego Lwa.\n" +
                        "\n" +
                        "Bliźnięta, którym również grozi opętanie, podejmują dramatyczną walkę na kilku frontach. Tymczasem na Arrakis pojawia się charyzmatyczny niewidomy Kaznodzieja, który bardzo przypomina ich zmarłego ojca..."),
                new Book("Bóg Imperator Diuny. Kroniki Diuny. Tom 4", "Science Fiction", "Herbert Frank", "Pokonawszy Alię i podporządkowawszy sobie nieustannie knujący intrygi ród Corrinów, Leto II objął rządy w Imperium i wprowadził je na swój Złoty Szlak. Trzy i pół tysiąca lat narzuconego spokoju zmieniło jednak niewiele. Bene Gesserit, Tleilaxanie, Ixanie i Gildia Kosmiczna – trzymani w ryzach groźbą odcięcia dostaw życiodajnego melanżu – gotowi są zrobić wszystko, by w końcu pozbyć się człowieka-czerwia.\n" +
                        "\n" +
                        "Syn legendarnego Muad’Diba staje w obliczu najbardziej diabolicznego spisku w historii. Spisku, który może doprowadzić nawet do śmierci Boga..."),
                new Book("Heretycy Diuny. Kroniki Diuny. Tom 5", "Science Fiction", "Herbert Frank", "Blisko półtora tysiąca lat po śmierci Boga Imperatora we wszechświecie dużo się zmieniło, jednak Tleilaxanie, Ixanie i Bene Gesserit pozostali na scenie, a nieżyjący Leto II wciąż kładzie się cieniem na ich przeszłości.\n" +
                        "\n" +
                        "Walcząc o supremację, a zarazem usiłując ocalić zgromadzenie przed napływającymi z Rozproszenia dostojnymi matronami, Bene Gesserit układają diaboliczny plan. W jego centrum tkwią kolejny ghola Duncana Idaho i żyjąca na Rakis dziewczyna, która… rozkazuje czerwiom."),
                new Book("Kapitularz Diuną. Kroniki Diuny. Tom 6", "Science Fiction", "Herbert Frank", "Kapitularz przechodzi z wolna transformację, w której wyniku Bene Gesserit mają nadzieję wznowić zbiory melanżu i zdobyć dominującą pozycję we wszechświecie. Toczy się jednak walka z czasem – Dostojne Matrony zaciskają nieustępliwie pętlę wokół głównej siedziby zgromadzenia. Dręczona wizjami własnej śmierci, Matka Przełożona Darwi Odrade robi wszystko, by ocalić siostry. Mimo sprzeciwu części z nich postanawia zagrać va banque i rzuca na szalę swe ostatnie atuty: gholę baszara Tega, byłą Dostojną Matronę Murbellę i obdarzonego niezwykłymi zdolnościami Duncana Idaho.\n" +
                        "\n" +
                        "Tymczasem gdzieś w oddali czai się wróg stokroć potężniejszy od Dostojnych Matron i zarzuca swą sieć..."),
                new Book("Outpost", "Science Fiction", "Glukhovsky Dmitry", "Mroczna, zaskakująca, wartka i kipiąca od emocji postapokalipsa mistrza gatunku – podtrzymująca najlepsze tradycje kultowego „Metra 2033”, a jednocześnie zupełnie inna!\n" +
                        "\n" +
                        "–A co jest tam, po drugiej stronie mostu?\n" +
                        "\n" +
                        "Historia Rosji praktycznie dobiegła kresu. Choć niektórzy usiłują nieporadnie dopisywać jej kolejne rozdziały, dla większości sprawa jest oczywista: to już koniec. Mimo że wciąż rodzą się dzieci, mimo że Jegor beznadziejnie zakochał się w Michelle, mimo że Pałkan nie ustaje w wydzwanianiu do Moskwy i błaganiu o uzupełnienie wyczerpujących się rezerw żywności… Tak, Rosji w zasadzie już nie ma; jej układ nerwowy – sieć połączeń transportowych i telekomunikacyjnych – został zerwany, zniszczony, a w najlepszym wypadku poważnie uszkodzony i nie wygląda na to, żeby zdołał się zregenerować. To dlatego te 250 kilometrów, jakie dzieli Moskwę od Placówki w Jarosławiu, wydaje się odległością zawrotną. To dlatego tam, po drugiej stronie mostu, nie ma już nic – oprócz pustych, martwych, uśpionych na wieki miast. Tak twierdzi Pałkan. I chciałby, żeby pogodził się z tym jego przybrany syn Jegor.\n" +
                        "\n" +
                        "Ale skoro tak, skoro za Wołgą świat zieje pustką, dlaczego most jest tak pilnie strzeżony? Dlaczego wartownicy tak intensywnie wpatrują się w otulającą go zieloną nieprzeniknioną mgłę? Dlaczego każdy szmer, szelest, pomruk, który dobiega zza kotary toksycznych wyziewów, stawia ich na nogi, a może nawet przeraża? Czyżby spodziewali się, że ktoś – lub coś – może stamtąd przyjść?\n" +
                        "\n" +
                        "I właśnie dziś przychodzi. Wyłania się z mgły. Przyniesie zbawienie czy zagładę? Odkupienie czy potępienie? Co wie, co ukrywa, czego chce? Jak splecie swój los z mieszkańcami jarosławskiej Placówki?"),
                new Book("Metro 2033", "Science Fiction", "Glukhovsky Dmitry", "Rok 2033. Świat po zagładzie nuklearnej. Ocalali walczą o przetrwanie w sieci moskiewskiego metra. Ich los trafia w ręce młodego Artema."),
                new Book("Morderstwo w Orient Expressie", "Kryminał", "Christie Agata", "W środku nocy Orient Express wpada w zaspy śnieżne. Na kilka dni zostają odcięci od świata. W zamkniętym od wewnątrz przedziale znaj dują podróżni zasztyletowanego mężczyznę. Dlaczego wszyscy pasażerowie kłamią podczas przesłuchania? Prawda jest tak dziwna, że nawet Piotrowi trudno w nią uwierzyć."),
                new Book("Dom zbrodni", "Kryminał", "Christie Agata", "Kiedy grecki milioner Arystydes Leonides zostaje otruty, podejrzenia padają na Brendę – młodą wdowę. Wnuczka ofiary Josephine to wścibska nastolatka – uwielbia kryminały i podsłuchuje, więc o krok wyprzedza policjantów. Trzeba uważnie słuchać, bo mordercy są próżni i lubią się chwalić! Ekscentryczni spadkobiercy, listy miłosne i panieński notes to tropy w śledztwie.\n" +
                        "\n" +
                        "\"Dom zbrodni\" jest wykładem na temat anatomii morderstwa, a przewrotne zakończenie i dziś może budzić sprzeciw. Nic dziwnego, że to ulubiona powieść Agaty Christie, która uważała, że „każdy jest zdolny do zbrodni”."),
                new Book("I nie było już nikogo", "Kryminał", "Christie Agata", "Najpopularniejsza powieść Agaty Chrisite, dawniej znana pod tytułem \"Dziesięciu małych murzynków\".\n" +
                        "\n" +
                        "Dziesięć osób, każda podejrzana o morderstwo, zostaje zaproszonych przez tajemniczego gospodarza do domu na wyspie. Gdy ginie druga osoba, goście szybko zdają sobie sprawę, że to, co początkowo uważali za nieszczęśliwy wypadek, jest robotą zabójcy. Postanawiają odkryć jego tożsamość, ale okazuje się, że nikt nie ma alibi. Odizolowani od społeczeństwa, niezdolni do opuszczenia miejsca pobytu, umierają jeden po drugim w sposób opisany w dziecięcej rymowance, która wywieszona jest w ich pokojach."),
                new Book("Szkoła latania. Kajko i Kokosz", "Komiks", "Christa Janusz", "Nieopodal Mirmiłowa własną warownię zbudowali niegodni miana rycerzy okrutni Zbójcerze, których wodza, Krwawego Hegemona, najbardziej złościło, kiedy słyszał, że nie ma poczucia humoru. Pewnego dnia postanowił zdobyć gród kasztelana Mirmiła, a atak rozpocząć od opanowania Klubo-Gospody, aby tym posunięciem szybko złamać ducha obrońców. Dzięki Kajkowi i Kokoszowi szturm zostaje odparty, dlatego Hegemon wymyśla sposób na pozbycie się obu wojów z grodu. Namawia kasztelana, aby ten spełnił swe największe marzenie i wraz z przybocznymi wyruszył na Łysą Górę znajdującą się w kraju Omsów, gdzie czarownice nauczą go latać."),
                new Book("Dzień Śmiechały. Kajko i Kokosz", "Komiks", "Christa Janusz", "W czasie polowania na grubego zwierza Kajko i Kokosz sami o mało nie stają się obiadem niezwykłego niedźwiedzia, przybierającego w nocy ludzką postać. Okazuje się Dziadem Borowym, opiekunem lasu, który zleca im misję odnalezienia wandali wycinających jego las. Wandalami okazują się Zbójcerze. Ale na co im tyle drewna? Otóż w pewien szczególny sposób chcą uczcić swój srebrny jubileusz – czyli 50. próbę zdobycia Mirmiłowa. Tego samego dnia w grodzie kasztelana wszyscy świętują jednak z innego powodu. Rankiem Kokosz budzi się co prawda we własnym łóżku, ale ustawionym na dachu chaty. Mirmił znajduje w pościeli jajko, które ponoć właśnie zniósł. Z dzbana po piwie Kowala-opoja wyskakują myszy. Co to wszystko znaczy?! Rozpoczął się Dzień Śmiechały – święto dowcipu!"),
                new Book("Festiwal czarownic. Kajko i Kokosz", "Komiks", "Christa Janusz", "Zbliża się setna rocznica założenia Mirmiłowa, która ma być hucznie uczczona, a w ramach zabaw odbędzie się przyciągający fanów z najdalszych zakątków kraju zlot czarownic – mega gwiazd plemiennej kultury popularnej. A że w przeddzień uroczystości Kajkowi i Kokoszowi udało się schwytać Wielkiego Hegemona, ten w zamian za uwolnienie przyrzekł prowadzić w swoim zamku pensjonat dla gości z odległych stron. Ale wcale nie zamierza zrezygnować z planów podbicia Mirmiłowa. W tajemnicy sprzymierza się z bandyckim plemieniem Rarogów, by przy ich wsparciu zawładnąć skarbami grodu. Nie przypuszcza wszakże, że własne plany snuje też podły wódz Rarogów, Walwuch."),
                new Book("Cudowny lek. Kajko i Kokosz", "Komiks", "Christa Janusz", "Najsłynniejsi słowiańscy wojowie komiksowi powracają! Dzielny i rozważny Kajko oraz samolubny Koko po raz kolejny rozbawią czytelników swymi przygodami. Znów będą bronić Mirmiłowa przed atakami niecnych zbójcerzy, oraz przeżyją całą masę przezabawnych przygód. Komiks autorstwa klasyka polskiego komiksu, Janusza Christy, to arcydzieło, które powinni przeczytać wszyscy - przygody i humor tej serii rozbawią zarówno młodych, jak i dorosłych czytelników."),
                new Book("Medyceusze. Tajemna historia dynastii", "Historyczne", "Hollingsworth Mary", "Medyceusze – fundatorzy najpotężniejszego europejskiego domu bankowego w XV wieku – władali Florencją od 1434 roku. Pod ich rządami miasto rozkwitło, stając się wiodącym ośrodkiem artystycznym i kolebką renesansu. Wtedy tworzyli Leonardo da Vinci, Botticelli, Donatello, Fra Angelico czy Michał Anioł. Ten znakomity ród wydał trzech papieży (Leon X, Leon XI, Klemens VII) oraz dwie królowe francuskie (Katarzyna Medycejska i Maria Medycejska).\n" +
                        "\n" +
                        "Czy ten utrwalony przez wieki wizerunek florenckiego rodu jest prawdziwy, czy może jest to fikcja powtarzana tak wiele razy, że zyskała status faktu? Czy Medyceusze faktycznie byli szlachetnymi mecenasami sztuki i humanizmu? A może, zupełnie jak Borgiowie, byli w rzeczywistości przebiegłymi, niemoralnymi tyranami, nienawidzonymi w mieście, w którym siłą przejęli władzę?"),
                new Book("Krótka historia archeologii", "Historyczne", "Fagan Brian", "Kiedy myślimy o archeologii, wyobrażamy sobie wykopaliska w poszukiwaniu starożytnych skarbów, przebijanie tuneli do wnętrza grobowców egipskich faraonów czy odkrywanie czaszek neandertalczyków. Jednak archeologia to znacznie więcej – to nauka bazująca na ludzkiej ciekawości tego, co było niegdyś. To nauka, która poszukuje świadectw dotyczących całej, liczącej ponad trzy miliony lat, historii człowieka. \n" +
                        "\n" +
                        "\"Krótka historia archeologii\" jest opowieścią o wybitnych archeologach i zdumiewających odkryciach, których dokonali w różnych zakątkach świata. Znajdziemy tu zarówno sztukę naskalną epoki lodowcowej, ruiny Majów, pierwszą osadę angielskich kolonistów w Jamestown, jak i tajemnicze Stonehenge czy Pompeje – miasto pogrzebane pod wulkanicznym popiołem w 79 roku n.e.\n" +
                        "\n" +
                        "W krótkich, pasjonujących rozdziałach Brian Fagan przedstawia rozwój archeologii od jej osiemnastowiecznych początków aż do czasów współczesnego postępu technicznego. Obserwujemy, jak przez wieki osiągano coraz bardziej wyrafinowane rozumienie przeszłości, wspomagane obecnie przez rewolucyjne zdobycze w dziedzinie teledetekcji i technik wykonywania zdjęć satelitarnych. W swojej książce, przeznaczonej dla czytelników w każdym wieku, Fagan przedstawia odkrycia, metody badawcze i niezwykle inspirujące postacie, które współtworzyły ogólnoświatowy sukces archeologii."),
                new Book("70 Wielkich Tajemnic Świata Starożytnego", "Historyczne", "Fagan Brian", "Pozycja dla czytelników zainteresowanych szeroko pojętą starożytnością, omawia mało znane zagadki z dawnych czasów oraz takie kwestie, jak choćby przyczyna upadku cywilizacji Majów, wokoło których narosło mnóstwo nierozstrzygniętych kontrowersji. Kilkunastu autorów - z których każdy jest wykładowcą uniwersyteckim i specjalistą w swojej dziedzinie - opracowało poszczególne rozdziały w oparciu o najnowsze znaleziska archeologiczne i najświeższe teorie. W porównaniu z prezentowanym tutaj poziomem wiedzy książki wydane w latach 90. ubiegłego wieku należy uznać za niekompletne bądź wręcz przestarzałe. Całość uzupełniają unikalne fotografie i niezbędne schematy. ")


        ));
        List<BookCopy> copies = copyRepository.saveAll(Arrays.asList(
                new BookCopy("7894161", LocalDate.now(), 255L, "KEN", books.get(0)),
                new BookCopy("9111611", LocalDate.now().minusDays(20), 125L, "KEN", books.get(1)),
                new BookCopy("39208590", LocalDate.now().minusWeeks(102), 608L, "Prószyński Media", books.get(2)),
                new BookCopy("16838581", LocalDate.now().minusWeeks(410), 352L, "Wydawnictwo Albatros", books.get(3)),
                new BookCopy("33003221", LocalDate.now().minusWeeks(152), 1104L, "Wydawnictwo Albatros", books.get(4)),
                new BookCopy("38795275", LocalDate.now().minusWeeks(140), 432L, "Wydawnictwo Albatros", books.get(5)),
                new BookCopy("38759949", LocalDate.now().minusWeeks(130), 152L, "Egmont Polska Sp. z o.o.", books.get(6)),
                new BookCopy("33979823", LocalDate.now().minusWeeks(130), 108L, "Egmont Polska Sp. z o.o.", books.get(7)),
                new BookCopy("34814833", LocalDate.now().minusWeeks(105), 784L, "Dom Wydawniczy Rebis", books.get(8)),
                new BookCopy("34919538", LocalDate.now().minusWeeks(210), 304L, "Dom Wydawniczy Rebis", books.get(9)),
                new BookCopy("34977163", LocalDate.now().minusWeeks(160), 576L, "Dom Wydawniczy Rebis", books.get(10)),
                new BookCopy("35734550", LocalDate.now().minusWeeks(410), 536L, "Dom Wydawniczy Rebis", books.get(11)),
                new BookCopy("36114061", LocalDate.now().minusWeeks(110), 624L, "Dom Wydawniczy Rebis", books.get(12)),
                new BookCopy("36287468", LocalDate.now().minusWeeks(178), 624L, "Dom Wydawniczy Rebis", books.get(13)),
                new BookCopy("18036930", LocalDate.now().minusWeeks(165), 592L, "Wydawnictwo Insignis", books.get(14)),
                new BookCopy("37064129", LocalDate.now().minusWeeks(195), 0L, "Wydawnictwo Insignis", books.get(15)),
                new BookCopy("65060070", LocalDate.now().minusWeeks(210), 264L, "Wydawnictwo Dolnośląskie", books.get(16)),
                new BookCopy("14197932", LocalDate.now().minusWeeks(310), 224L, "Wydawnictwo Dolnośląskie", books.get(17)),
                new BookCopy("65919972", LocalDate.now().minusWeeks(342), 216L, "Wydawnictwo Dolnośląskie", books.get(18)),
                new BookCopy("36673087", LocalDate.now().minusWeeks(251), 44L, "Egmont Polska Sp. z o.o.", books.get(19)),
                new BookCopy("35319344", LocalDate.now().minusWeeks(125), 44L, "Egmont Polska Sp. z o.o.", books.get(20)),
                new BookCopy("35120858", LocalDate.now().minusWeeks(354), 44L, "Egmont Polska Sp. z o.o.", books.get(21)),
                new BookCopy("36857661", LocalDate.now().minusWeeks(334), 44L, "Egmont Polska Sp. z o.o.", books.get(22)),
                new BookCopy("38082238", LocalDate.now().minusWeeks(244), 536L, "Wydawnictwo Bellona", books.get(23)),
                new BookCopy("37248604", LocalDate.now().minusWeeks(574), 288L, "Wydawnictwo RM", books.get(24)),
                new BookCopy("68492830", LocalDate.now().minusWeeks(324), 304L, "Wydawnictwa Debit", books.get(25)),
                //###############################################################################################
                new BookCopy("7894161", LocalDate.now(), 255L, "KEN", books.get(0)),
                new BookCopy("9111611", LocalDate.now().minusDays(20), 125L, "KEN", books.get(1)),
                new BookCopy("39208590", LocalDate.now().minusWeeks(102), 608L, "Prószyński Media", books.get(2)),
                new BookCopy("16838581", LocalDate.now().minusWeeks(410), 352L, "Wydawnictwo Albatros", books.get(3)),
                new BookCopy("33003221", LocalDate.now().minusWeeks(152), 1104L, "Wydawnictwo Albatros", books.get(4)),
                new BookCopy("38795275", LocalDate.now().minusWeeks(140), 432L, "Wydawnictwo Albatros", books.get(5)),
                new BookCopy("38759949", LocalDate.now().minusWeeks(130), 152L, "Egmont Polska Sp. z o.o.", books.get(6)),
                new BookCopy("33979823", LocalDate.now().minusWeeks(130), 108L, "Egmont Polska Sp. z o.o.", books.get(7)),
                new BookCopy("34814833", LocalDate.now().minusWeeks(105), 784L, "Dom Wydawniczy Rebis", books.get(8)),
                new BookCopy("34919538", LocalDate.now().minusWeeks(210), 304L, "Dom Wydawniczy Rebis", books.get(9)),
                new BookCopy("34977163", LocalDate.now().minusWeeks(160), 576L, "Dom Wydawniczy Rebis", books.get(10)),
                new BookCopy("35734550", LocalDate.now().minusWeeks(410), 536L, "Dom Wydawniczy Rebis", books.get(11)),
                new BookCopy("36114061", LocalDate.now().minusWeeks(110), 624L, "Dom Wydawniczy Rebis", books.get(12)),
                new BookCopy("36287468", LocalDate.now().minusWeeks(178), 624L, "Dom Wydawniczy Rebis", books.get(13)),
                new BookCopy("18036930", LocalDate.now().minusWeeks(165), 592L, "Wydawnictwo Insignis", books.get(14)),
                new BookCopy("37064129", LocalDate.now().minusWeeks(195), 0L, "Wydawnictwo Insignis", books.get(15)),
                new BookCopy("65060070", LocalDate.now().minusWeeks(210), 264L, "Wydawnictwo Dolnośląskie", books.get(16)),
                new BookCopy("14197932", LocalDate.now().minusWeeks(310), 224L, "Wydawnictwo Dolnośląskie", books.get(17)),
                new BookCopy("65919972", LocalDate.now().minusWeeks(342), 216L, "Wydawnictwo Dolnośląskie", books.get(18)),
                new BookCopy("36673087", LocalDate.now().minusWeeks(251), 44L, "Egmont Polska Sp. z o.o.", books.get(19)),
                new BookCopy("35319344", LocalDate.now().minusWeeks(125), 44L, "Egmont Polska Sp. z o.o.", books.get(20)),
                new BookCopy("35120858", LocalDate.now().minusWeeks(354), 44L, "Egmont Polska Sp. z o.o.", books.get(21)),
                new BookCopy("36857661", LocalDate.now().minusWeeks(334), 44L, "Egmont Polska Sp. z o.o.", books.get(22)),
                new BookCopy("38082238", LocalDate.now().minusWeeks(244), 536L, "Wydawnictwo Bellona", books.get(23)),
                new BookCopy("37248604", LocalDate.now().minusWeeks(574), 288L, "Wydawnictwo RM", books.get(24)),
                new BookCopy("68492830", LocalDate.now().minusWeeks(324), 304L, "Wydawnictwa Debit", books.get(25)),
                new BookCopy("7894161", LocalDate.now(), 255L, "KEN", books.get(0)),
                new BookCopy("9111611", LocalDate.now().minusDays(20), 125L, "KEN", books.get(1)),
                new BookCopy("39208590", LocalDate.now().minusWeeks(102), 608L, "Prószyński Media", books.get(2)),
                new BookCopy("16838581", LocalDate.now().minusWeeks(410), 352L, "Wydawnictwo Albatros", books.get(3)),
                new BookCopy("33003221", LocalDate.now().minusWeeks(152), 1104L, "Wydawnictwo Albatros", books.get(4)),
                new BookCopy("38795275", LocalDate.now().minusWeeks(140), 432L, "Wydawnictwo Albatros", books.get(5)),
                new BookCopy("38759949", LocalDate.now().minusWeeks(130), 152L, "Egmont Polska Sp. z o.o.", books.get(6)),
                new BookCopy("33979823", LocalDate.now().minusWeeks(130), 108L, "Egmont Polska Sp. z o.o.", books.get(7)),
                new BookCopy("34814833", LocalDate.now().minusWeeks(105), 784L, "Dom Wydawniczy Rebis", books.get(8)),
                new BookCopy("34919538", LocalDate.now().minusWeeks(210), 304L, "Dom Wydawniczy Rebis", books.get(9)),
                new BookCopy("34977163", LocalDate.now().minusWeeks(160), 576L, "Dom Wydawniczy Rebis", books.get(10)),
                new BookCopy("35734550", LocalDate.now().minusWeeks(410), 536L, "Dom Wydawniczy Rebis", books.get(11)),
                new BookCopy("36114061", LocalDate.now().minusWeeks(110), 624L, "Dom Wydawniczy Rebis", books.get(12)),
                new BookCopy("36287468", LocalDate.now().minusWeeks(178), 624L, "Dom Wydawniczy Rebis", books.get(13)),
                new BookCopy("18036930", LocalDate.now().minusWeeks(165), 592L, "Wydawnictwo Insignis", books.get(14)),
                new BookCopy("37064129", LocalDate.now().minusWeeks(195), 0L, "Wydawnictwo Insignis", books.get(15)),
                new BookCopy("65060070", LocalDate.now().minusWeeks(210), 264L, "Wydawnictwo Dolnośląskie", books.get(16)),
                new BookCopy("14197932", LocalDate.now().minusWeeks(310), 224L, "Wydawnictwo Dolnośląskie", books.get(17)),
                new BookCopy("65919972", LocalDate.now().minusWeeks(342), 216L, "Wydawnictwo Dolnośląskie", books.get(18)),
                new BookCopy("36673087", LocalDate.now().minusWeeks(251), 44L, "Egmont Polska Sp. z o.o.", books.get(19)),
                new BookCopy("35319344", LocalDate.now().minusWeeks(125), 44L, "Egmont Polska Sp. z o.o.", books.get(20)),
                new BookCopy("35120858", LocalDate.now().minusWeeks(354), 44L, "Egmont Polska Sp. z o.o.", books.get(21)),
                new BookCopy("36857661", LocalDate.now().minusWeeks(334), 44L, "Egmont Polska Sp. z o.o.", books.get(22)),
                new BookCopy("38082238", LocalDate.now().minusWeeks(244), 536L, "Wydawnictwo Bellona", books.get(23)),
                new BookCopy("37248604", LocalDate.now().minusWeeks(574), 288L, "Wydawnictwo RM", books.get(24)),
                new BookCopy("68492830", LocalDate.now().minusWeeks(324), 304L, "Wydawnictwa Debit", books.get(25)),
                new BookCopy("34814833", LocalDate.now().minusWeeks(105), 784L, "Dom Wydawniczy Rebis", books.get(8)),
                new BookCopy("34919538", LocalDate.now().minusWeeks(210), 304L, "Dom Wydawniczy Rebis", books.get(9)),
                new BookCopy("34977163", LocalDate.now().minusWeeks(160), 576L, "Dom Wydawniczy Rebis", books.get(10)),
                new BookCopy("35734550", LocalDate.now().minusWeeks(410), 536L, "Dom Wydawniczy Rebis", books.get(11)),
                new BookCopy("36114061", LocalDate.now().minusWeeks(110), 624L, "Dom Wydawniczy Rebis", books.get(12)),
                new BookCopy("36287468", LocalDate.now().minusWeeks(178), 624L, "Dom Wydawniczy Rebis", books.get(13)),
                new BookCopy("34814833", LocalDate.now().minusWeeks(105), 784L, "Dom Wydawniczy Rebis", books.get(8)),
                new BookCopy("34919538", LocalDate.now().minusWeeks(210), 304L, "Dom Wydawniczy Rebis", books.get(9)),
                new BookCopy("34977163", LocalDate.now().minusWeeks(160), 576L, "Dom Wydawniczy Rebis", books.get(10)),
                new BookCopy("35734550", LocalDate.now().minusWeeks(410), 536L, "Dom Wydawniczy Rebis", books.get(11)),
                new BookCopy("36114061", LocalDate.now().minusWeeks(110), 624L, "Dom Wydawniczy Rebis", books.get(12)),
                new BookCopy("36287468", LocalDate.now().minusWeeks(178), 624L, "Dom Wydawniczy Rebis", books.get(13)),
                new BookCopy("39208590", LocalDate.now().minusWeeks(102), 608L, "Prószyński Media", books.get(2)),
                new BookCopy("16838581", LocalDate.now().minusWeeks(410), 352L, "Wydawnictwo Albatros", books.get(3)),
                new BookCopy("33003221", LocalDate.now().minusWeeks(152), 1104L, "Wydawnictwo Albatros", books.get(4)),
                new BookCopy("38795275", LocalDate.now().minusWeeks(140), 432L, "Wydawnictwo Albatros", books.get(5)),
                new BookCopy("38759949", LocalDate.now().minusWeeks(130), 152L, "Egmont Polska Sp. z o.o.", books.get(6)),
                new BookCopy("33979823", LocalDate.now().minusWeeks(130), 108L, "Egmont Polska Sp. z o.o.", books.get(7)),
                new BookCopy("39208590", LocalDate.now().minusWeeks(102), 608L, "Prószyński Media", books.get(2)),
                new BookCopy("16838581", LocalDate.now().minusWeeks(410), 352L, "Wydawnictwo Albatros", books.get(3)),
                new BookCopy("33003221", LocalDate.now().minusWeeks(152), 1104L, "Wydawnictwo Albatros", books.get(4)),
                new BookCopy("38795275", LocalDate.now().minusWeeks(140), 432L, "Wydawnictwo Albatros", books.get(5)),
                new BookCopy("38759949", LocalDate.now().minusWeeks(130), 152L, "Egmont Polska Sp. z o.o.", books.get(6)),
                new BookCopy("33979823", LocalDate.now().minusWeeks(130), 108L, "Egmont Polska Sp. z o.o.", books.get(7)),
                new BookCopy("39208590", LocalDate.now().minusWeeks(102), 608L, "Prószyński Media", books.get(2)),
                new BookCopy("16838581", LocalDate.now().minusWeeks(410), 352L, "Wydawnictwo Albatros", books.get(3)),
                new BookCopy("33003221", LocalDate.now().minusWeeks(152), 1104L, "Wydawnictwo Albatros", books.get(4)),
                new BookCopy("38795275", LocalDate.now().minusWeeks(140), 432L, "Wydawnictwo Albatros", books.get(5)),
                new BookCopy("38759949", LocalDate.now().minusWeeks(130), 152L, "Egmont Polska Sp. z o.o.", books.get(6)),
                new BookCopy("33979823", LocalDate.now().minusWeeks(130), 108L, "Egmont Polska Sp. z o.o.", books.get(7)),
                new BookCopy("18036930", LocalDate.now().minusWeeks(165), 592L, "Wydawnictwo Insignis", books.get(14)),
                new BookCopy("37064129", LocalDate.now().minusWeeks(195), 0L, "Wydawnictwo Insignis", books.get(15)),
                new BookCopy("65060070", LocalDate.now().minusWeeks(210), 264L, "Wydawnictwo Dolnośląskie", books.get(16)),
                new BookCopy("14197932", LocalDate.now().minusWeeks(310), 224L, "Wydawnictwo Dolnośląskie", books.get(17)),
                new BookCopy("65919972", LocalDate.now().minusWeeks(342), 216L, "Wydawnictwo Dolnośląskie", books.get(18)),
                new BookCopy("36673087", LocalDate.now().minusWeeks(251), 44L, "Egmont Polska Sp. z o.o.", books.get(19)),
                new BookCopy("35319344", LocalDate.now().minusWeeks(125), 44L, "Egmont Polska Sp. z o.o.", books.get(20)),
                new BookCopy("35120858", LocalDate.now().minusWeeks(354), 44L, "Egmont Polska Sp. z o.o.", books.get(21)),
                new BookCopy("36857661", LocalDate.now().minusWeeks(334), 44L, "Egmont Polska Sp. z o.o.", books.get(22)),
                new BookCopy("38082238", LocalDate.now().minusWeeks(244), 536L, "Wydawnictwo Bellona", books.get(23)),
                new BookCopy("37248604", LocalDate.now().minusWeeks(574), 288L, "Wydawnictwo RM", books.get(24)),
                new BookCopy("68492830", LocalDate.now().minusWeeks(324), 304L, "Wydawnictwa Debit", books.get(25)),
                new BookCopy("18036930", LocalDate.now().minusWeeks(165), 592L, "Wydawnictwo Insignis", books.get(14)),
                new BookCopy("37064129", LocalDate.now().minusWeeks(195), 0L, "Wydawnictwo Insignis", books.get(15)),
                new BookCopy("65060070", LocalDate.now().minusWeeks(210), 264L, "Wydawnictwo Dolnośląskie", books.get(16)),
                new BookCopy("14197932", LocalDate.now().minusWeeks(310), 224L, "Wydawnictwo Dolnośląskie", books.get(17)),
                new BookCopy("65919972", LocalDate.now().minusWeeks(342), 216L, "Wydawnictwo Dolnośląskie", books.get(18)),
                new BookCopy("36673087", LocalDate.now().minusWeeks(251), 44L, "Egmont Polska Sp. z o.o.", books.get(19)),
                new BookCopy("35319344", LocalDate.now().minusWeeks(125), 44L, "Egmont Polska Sp. z o.o.", books.get(20)),
                new BookCopy("35120858", LocalDate.now().minusWeeks(354), 44L, "Egmont Polska Sp. z o.o.", books.get(21)),
                new BookCopy("36857661", LocalDate.now().minusWeeks(334), 44L, "Egmont Polska Sp. z o.o.", books.get(22)),
                new BookCopy("38082238", LocalDate.now().minusWeeks(244), 536L, "Wydawnictwo Bellona", books.get(23)),
                new BookCopy("37248604", LocalDate.now().minusWeeks(574), 288L, "Wydawnictwo RM", books.get(24)),
                new BookCopy("68492830", LocalDate.now().minusWeeks(324), 304L, "Wydawnictwa Debit", books.get(25)),
                new BookCopy("36673087", LocalDate.now().minusWeeks(251), 44L, "Egmont Polska Sp. z o.o.", books.get(19)),
                new BookCopy("35319344", LocalDate.now().minusWeeks(125), 44L, "Egmont Polska Sp. z o.o.", books.get(20)),
                new BookCopy("35120858", LocalDate.now().minusWeeks(354), 44L, "Egmont Polska Sp. z o.o.", books.get(21)),
                new BookCopy("36857661", LocalDate.now().minusWeeks(334), 44L, "Egmont Polska Sp. z o.o.", books.get(22)),
                new BookCopy("36673087", LocalDate.now().minusWeeks(251), 44L, "Egmont Polska Sp. z o.o.", books.get(19)),
                new BookCopy("35319344", LocalDate.now().minusWeeks(125), 44L, "Egmont Polska Sp. z o.o.", books.get(20)),
                new BookCopy("35120858", LocalDate.now().minusWeeks(354), 44L, "Egmont Polska Sp. z o.o.", books.get(21)),
                new BookCopy("36857661", LocalDate.now().minusWeeks(334), 44L, "Egmont Polska Sp. z o.o.", books.get(22))

        ));

//        List<Borrowing> borrowings = borrowingRepo.saveAll(Arrays.asList(
//           new Borrowing(LocalDateTime.now(), LocalDateTime.now().plusDays(15))
//        ));


//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(new LibraryUser("test2", passwordEncoder.encode("pass"), "email2@email.email", "LIBRARIAN")));
//        bookRepo.saveAll(Arrays.asList(
//                new Book("Pan Tadeusz", "Romance", "Mickiewicz Adam", "Borring AF", LocalDate.now(), 247L),
//                new Book("Quo Vadis", "Historical", "Sienkiewicz Henryk", "Rome stuff", LocalDate.now(), 380L)
//        ));

//        System.out.println(userRepo.findById(1L).get().toString());
//        System.out.println(bookRepo.findById(1L).get().toString());
//        borrowingRepo.saveAll(Arrays.asList(
//                new Borrowing(userRepo.findById(1L).get(), bookRepo.findById(1L).get(), OffsetDateTime.now(), OffsetDateTime.now().plusDays(10))
//        ));
    }
}
