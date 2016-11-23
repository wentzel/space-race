/**
 * @author Airaksinen, Tom & Wentzel, Jonatan
 *
 * The main class for the splendid game that will redefine the concept of 
 * electronic entertainment and hopefully contribute to world peace.
 * 
 */

/*
 * EFTER INLÄMNING:
 * 
 * att göra:
 *  - Game starting in 3, 2, 1
 *  - Avbryt = avbryt vid inmatning av spel
 *  - Blink vid fel del
 *  - Rök innan uppskjut
 *  - Ljud
 *  - GUI för att byta färger + ikoner (actors + locations) (iställningar
 *    bör sparas)
 * 
 * 
 * Jonatan:
 * 
 * - Uppritningen av hela raketen görs nu med nullayout, d.v.s stor förändring.
 *   Rakterna vibrerar i x-led med en hypad sinusfunktion och accelererar i
 *   y-led med en fager exponentialfunktion. GameWorkshop heter nu istället
 *   RocketPanel.
 * 
 * - Spelarna kan nu återigen gå diagonalt.Blir dock inte helt snyggt eftersom
 *   inga diagonala bilder finns. Det blir inte heller helt bra eftersom
 *   diagonalt verkligen betyder diagonalt, inte t.ex. vänster om det finns
 *   en vägg uppåt och man håller ner vänster och uppåt (getNextLocation ser
 *   ju att det är en vägg snett uppåt vänster)
 * 
 * - Upplägget för ikoner till GameLocation har ändrats. Nu har alla subklasser
 *   till GameLocation en lokal variabel type som berättar vilken typ en instans
 *   är. Sedan har Wall resp. Floor statiska variabler för de olika typerna av
 *   ikoner. Vid Draw(g) anropas getImage() som kollar vilken typ instansen har
 *   och returnerar sedan rätt bild.
 * 
 * FÖRE INLÄMNING:
 * 
 * 
 *    Jonatan har fixat följande:
 * - Fixade till SuperSuckerns suck. Nu ska allt vara perfa!
 * 
 * - Fixade så att save- och loadHighScore läser och skriver hela listan som
 *   ett objekt.
 * 
 * - Fixade så att highscorelistan sorteras efter att e1 har lagts till.
 * 
 * - Bytte namn på GameAreaLabyrinth till GameArea och finnkampen 2067 till
 *   SpaceRace
 * 
 * - Move för SuperSucker kanske behöver lite inside-kommentarer
 * 
 * - Allt annat färdigkodat (tror jag)
 * 
 * - Tyvärr brakar spelet när SuperSuckern hittar dammet på marken...
 * 
 * 1) Tom har fixat följande:
 * - Lade in ett Instructions window (glöm inte att uppdatera klassdiagrammet!)
 *   Tror jag kom ihåg att lägga in alla grejer rätt (mnemonics, ctrl, uppdatera
 *   hjälptexter, titelrad i fönstret), men dubbelkolla gärna
 * 
 * - Fixade en BUTTON_COLOR i GameWindow, som används till knapparna. De är nu
 *   något ljusare än bakgrunden.
 * 
 * - Döpte om och städade bland bilderna i img-katalogen. Se separat mejl!
 * 
 * - Kommenterade till och med bokstaven G. Börja med HighScoreEntry du!
 * 
 * - Lade till så att HighScoreEntry implementerar Comparable och därmed kan
 *   sorteras automatiskt med "Collections.sort(highscorelistan)"
 *   (just den syntaxen). (Uppdatera klassdiagrammet med även denna nya
 *   implements-grej.)
 * 
 * - Skrev om addHighScores(e1, e2) i GameWindow till en i mitt tycke snyggare
 *   variant som bara behöver gå igenom listan en gång. Tidigare metod finns
 *   kvar precis ovanför, men är bortkommenterad.
 * 
 * - Ändrade get&set-raden till
 * 
 *    // ------------------- Get&Sets of relevant variables -------------------
 *    
 *    (Två blankrader ovanför, en under)
 * 
 *    Jag har lagt in den i alla relevanta klasser (hoppas jag).
 * 
 * 
 * 2) Att-göra-1: Fixa enhetlighet när det gäller följande:
 * 
 * - Blankrad (som ovan) eller inte (som i Dust.java) mellan sista raden i vår
 *   textbeskrivning och sluttaggen (asterisk-slash) i javadoc-kommentaren
 *   för klasser - JA, blankrad i kommentar till klasser!
 * 
 * - Skall vi ha blankrad mellan beskrivning och @param- och @return-rader?
 *   Nu är det så på vissa ställen, men inte på andra. Jag tycker att det är
 *   snyggt om man har många params och returns, men det känns onödigt om
 *   det bara är en sådan efter själva beskrivningen. Är det viktigt med samma
 *   princip överallt? Kan den principen vara "om endast ett argument -> ingen
 *   blankrad"? - JA!
 * 
 * - Skall vi ha javadoc-kommentarer på bara en rad? De allra flesta metoder
 *   tar antingen ett argument eller returnerar något. Enradskommentarerna
 *   riskerar att försvinna lite och se ut som vanliga slash-slash-kommentarer.
 *   - Inga enradskommentarer!
 * 
 * - Ordning på get och set, get först - sen set. - JA!
 * 
 * 
 * 3) Att-göra-2: Fixa till följande grejer:
 * 
 * - Kommentera instansvariabler med icke-uppenbara namn eller syften?
 * 
 * - Varför är metoden createLocationMatrix i GameWorld private? Skadar inte,
 *   men inga andra metoder är väl privata? Byt till public? - FIXAT!
 * 
 * - Kan det faktum att HighScoreEntry nu implementerar Comparable användas
 *   även på andra ställen för att snygga till koden. Jag hängde inte riktigt
 *   med i svängarna på varför det skulle vara rank med i konstruktorerna
 *   (till entrysarna) och sånt. Förmodligen är detta bara något gammalt arv,
 *   men du har bäst koll! - FIXAT!
 * 
 * 
 *  
 * 
 * 
 * 
 * Att göra:
 * - Fixa till bilderna så att man kan se skillnad på delarna
 *   (Vingar för små och otydliga, motor för stor, nozzlekonturen
 *    skall fyllas i så hela främre konen syns - inget M)
 * - Fixa gubbarna (bilder)
 * - Fixa så att man inte kan öppna fler kopior av fönstren (high score m.fl.)
 * - Fixa ljudeffekter (dammsugaren: schlurrp!)
 * - Fixa möjlighet att stänga av ljudet
 * - Cleanare high-scorelista (släng bara in båda entries och sortera sedan)?
 * 
 * 
 * 
 * 
 * *********** TOMS PILL TIS 11/1 *************
 * - Nu kan man gå igenom spöket när det är genomskinligt. (Opacity < 0.3f,
 *   vilket innebär problem eftersom spökenas opacitetsvariationer slumpas fram.
 *   En del spöken kanske man typ aldrig kan gå igenom, andra kanske man kan
 *   gå igenom typ hela tiden... Tror också att vi borde fixa en bättre
 *   rutin för kollisionshantering. Tror min slöar ner spelet FETT!
 * 
 * - Lade till en decreaseScore i Player på samma sätt som increaseScore.
 *   Anropas med argumentet 100 om man går igenom ett spöke. Vissa kollisioner
 *   detekteras av någon anledning inte (eller så minskas bara inte poängen).
 * 
 * - Fixade så att dammsugaren snor den del man bär på. För varje steg som
 *   sugaren flyttar sig är det 10 % chans att den släpper objektet (om
 *   det inte redan ligger något i den rutan).
 *
 * - Lade till relaseCarriedObject() och setCarriedObject(Gameobject obj)
 *   i SuperSucker.
 * 
 * - Lade till loseObjectToSucker() i Player. Anropas om man bär på något som
 *   dammsugaren snor.
 * 
 * - Lade till så att dammsugaren även suger upp delar som den åker över.
 * 
 * - Se punkterna inom OBS! nedan! Nu måste jag dra till Edvard!
 * 
 * OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! 
 * 
 * MÅSTE FIXA SPECIALFALLET MED DAMM... Skall man kunna plocka upp det?
 * Skall dammsugaren då "suga upp" det om han krockar med en?
 * 
 * Måste också fixa vad som händer om sugaren har med sig en del som den
 * sugit upp från marken och går in i en. Då skall han inte sno ens egen del
 * va? Eller?
 * 
 * OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS!
 * 
 * - Fusk (steal)
 * - Kaffemaskin, waiter
 * - Ljud
 * - Fixa större värld - FIXAT
 * - Nätverk
 * - Fixa slumpen till floorImageList
 * - Flytande gång
 * - GameAreaWorkshop
 * - Fixa en ruta i workshop/panel där det man bär på visas - FIXAT, men inte
 * 			helt snyggt. Jag tror att vi borde fundera lite på layouten. Det
 *   		blir inget bra med knapparna där de är, vi borde nog sätta dem
 *  		i north i stället, det börjar bli för lite plats för player1s
 * 			rymdskepp att åka på.
 * 
 *
 * - välja ok/avbryt med tangentbordet. - funkare ej...men space funkar...
 *   jag börjar fatta varför, enter funkar på vår egna meny, men inte på
 *   typ JOptionDialogue eftersom vi inte har möjlighet att / vet hur man
 *   ändrar detta i dessa, och det verkar inte finnas någon bra konstruktor
 *   som tar argument för att ändra detta, det verkar helt enkelt vara en
 *   miss i Java 1.4.2 (förhopnningsvis bättre i 1.5). Liknande problem 
 *   har vi i vår egna dialogruta showGameAlternatives. Där kan man inte
 *   ens använda ShowMnemonics (som är default i showOptionDialogue) för
 *   att använda Alt-h för highScore etc.
 *
 *
 * - Quit-problemet vid nytt spel vid gameOver - FIXAT, av någon mystisk
 *   anledning exekverades else-satsen i showGameAlternative trots att alt
 *   fortfarande = 0. Hursom blir det bra när man istället skriver else if
 *   (alt == 2).
 * - Kollisionshantering
 * - Förbättra highscorelistan - FIXAT!
 * - Applet - Försökt, problem med rättigheter för att öppna bilder
 * - Symmetrisk värld - FIXAT! Man ska nu bara rita upp halva världen.
 * 
 * - Utplaceringen av AboutWindow och HighScoreWindow görs nu någorlunda
 *   likadant. Dock borde vi se till att de görs exakt likadant!
 * 
 * - RocketModule måste innehålla en panelImage och en labyrinthImage. Jag
 *   la dock till dessa i GameObject (labyrinthImage hette förut bara image).
 *   Detta gjorde också att koden i GameAreaWorkshop blev mycket snyggare
 *   när vi kollar vilken bild som ska läggas till i workshopen.
 * 
 * - Hmm...något mysko att kollisionshanteringen ligger i GameActor, känns
 *   som att den borde ha att göra med legalMovefunktionen som väl ligger i
 *   GameWorld...
 *
 *   ************ JONATAN - TISDAG KVÄLL ********''
 * - Fixade spelarnamn. Man får skriva in dem i början av varje spel
 *   Om man har restartad så är default namnen från förra spelet. Detta löstes
 *   väl inte helt snyggt, men metoden setPlayerNames finns i GameWindow och
 *   kan anropas med inga argument (inga defaultnamn i rutan) eller med två
 *   strängar (defaulttexterna).
 * 
 * - Fixade så att den som har mest poäng vinner. Detta står i Congrats-rutan.
 * 
 * - Provspelade med min utbytesstudentgranne, han tycket spelet var grymt!
 * 
 * 
 * 
 *   *************TOMS STRUL TISDAG EFTERMIDDAG/KVÄLL********
 * - Fixat olika ikoner för spelare 1 och 2. Tyvärr får vi inte skicka
 *   en int till konstruktorn, och sedan välja bilder att skicka
 *   till superkonstruktorn efter den. Konstruktoranropet måste ju
 *   komma först! Tredje gången vi går i denna fällla.... Ett alternativ
 *   är att göra en mellannivå, typ public GamePlayer createPlayer(int num)
 *   som säger return new Player(blah blah), men jag tyckte det blev lite
 *   ointuitivt, så jag struntade i det tills vidare...
 * 
 * - Fixat insets på fönsterstorleken. Att det inte funkade förut berodde
 *   nog på att vi inte hade kört pack().
 * 
 * - Bytt ordning på knapparna i panelen så att New Game är överst och Quit
 *   underst...
 * 
 * - Fixat svart bakgrund och vit, centrerad text i poängfälten
 * 
 * - Gjort ett fulhack i changeRocketClearing som kollar om spelaren
 *   skickat upp 2 raketer (jag tycker att man skall vinna just där,
 *   när raketen når taket, och inte när man skjuter upp den). Då visas
 *   en enkel dialogruta. När man klickar OK startas spelet om.
 * 
 * - Kommenterat bort metoden createLocationMatrix2 (den gamla, innan
 *   vi gick över till strängar). Du kan ta bort den om du vill.
 * 
 * - Återställt så att alla delar slumpas ut. Jag kommenterade bara bort hård-
 *   kodningen av alla nya raketdelar nära spelare 1. Bra att kunna återställa
 *   senare.
 * 
 * - Lade in ett spöke. Det går assegt på den här datorn. Inte minst när en
 *   raket skjuts upp!
 * 
 * - Redigerade att-göra-listan här ovanför. Rensade ut en hel del som redan
 *   är fixat och lade in att vi skall göra en välkomstruta med instruktioner.
 * 
 * 
 * 
 * 
 * 
 * 
 *  
 * Att göra för Tom onsdag-torsdag:
 * - Rendera grafik (men inte allt)
 * - Byt världen från charmatris till strängarray  --- NEJ! Se nedan!
 * - Knappar (New Game, Restart, Quit, About)
 * 
 * Att göra för Jonatan lö-sö
 * - Byt världen från charmatris till strängarray - Jo! se världen! - FIXAT!
 * 
 * - Ev. flytta ut "legalmove"-delen ur moveTo i GameActor till legalMove
 *   i GameWorld (ev. returnera int istället, 0=ok, 1=vägg, 2=actor ivägen
 * 
 * - Istället för att ta bort obj från listan av obj i världen kanske deras
 *   location ska sättas till null? Då slipps "removeObjectFromWorld" - FIXAT!
 * 
 * - Fler knappar: New Game, About, Restart (Save? Load?) - Delvis
 * 
 * - Tangentbordskommandon Ctrl-Q - Delvis
 * 
 * - Fusk!!! (manipulera legalMove()) (gå utanför labyrinten och sno
 *   den andres del. - Delvis
 * 
 * - Bygga upp raketerna, dvs hända något när man lägger obj i targetrutan.
 *   - FIXAT!
 * 
 * 
 * JONATANS FÖRÄNDRINGAR LÖRDAG-SÖNDAG:
 * LÖRDAG:
 * - Började kolla på dammsugaren, kom ingen vart.
 * 
 * - Tänkte implementera en restartknapp för effektivare testning.
 *   Blev förstås mycket mer komplicerat än förväntat. restart() lades till i
 *   GameAreaLabyrinth, och panelen känner numera till labyrinten (för att
 *   kunna restarta den). Tyvärr görs detta inte helt snyggt, se konstruktorn
 *   för GameWindow som måste använda panel.setLabyrinth(labyrinth) eftersom
 *   labyrintkonstruktorn behöver panelen och panelkonstruktorn behöver
 *   laybrinten. Detta tror jag tyder på att vi har ett stort konceptuellt
 *   tankefel någonstans i upplägget med klasser (eller också så kanske vissa
 *   metoder bara ligger i fel klasser?)... Ctrl-n funkar också
 *   för att restarta.
 * 
 * - Paus är implementerat. knapp p stoppar respektive resumar timern i
 *   GameWindow. I denna klass finns en boolean som visar om spelet är
 *   pausat eller ej. Metoderna som har med detta att göra anropar först
 *   getTopLevelAncestor() för att hämta det GameWindow som de ligger i (måste
 *   castas)
 * 
 * - Eventuellt borde det i båda dessa lägen (spelet pausat samt restartat)
 *   komma upp en liten text ("Game Paused, press p to resume" "Game restarted,
 *   starting in 3, 2, 1 seconds"). Fixade även lite font på dessa meddelanden.
 * 
 * - Ett lurigt fusk är implementerat! När man har pausat kan man styra gubben
 *   när man sedan trycker p igen så "teleporteras" gubben. På så sätt kan man
 *   t.ex. hinna förbi dammsugaren som annars kanske skulle blockera vägen
 *   (vaddå bug som jag inte har en aning om hur man fixar? :)
 * 
 * - Fixade stringWorld som representerar världen med String[] i stället för
 *   char[][]. Det blev fortfarande inte helt symmetriskt, men helt ok.
 *   Först körde jag med kommatecken som separator, men det såg inte så snyggt
 *   ut så jag fixade till det med space som separator. Jag sparade dock koden
 *   till kommaseparationen som ser snyggare ut så får vi besluta vilket som
 *   är bäst. I och med detta fixade jag också en lite större värld.
 * 
 * - La till setResizable(false) i GameWindow. Detta gjorde ramarna till
 *   fönstret något tunnare så jag minskade fönstrets storlek med 2x2 px 
 *   (vi borde kolla upp det där med "getBorderThickness").
 * 
 * - Satte tillbaks den gamla composite i SuperSucker, annars används alpha-
 *   blinkningen på allt som man ritar själv (t.ex. paus-texten).
 * 
 * - Behandlingen av upplockande och nedsättande av objekt förbättrades.
 *   Nu används inte längre metoderna addObject och removeObject i GameWorld.
 *   Istället sätts objektens location till null och vid utritningen av dessa
 *   kontrolleras om location är null, om så är fallet ritas objektet ej ut.
 * 
 * - Ändrat upplägget i panelen för att kunna lägga till RocketModules allt
 *   eftersom. Jag införde GameAreaWorkshop som läggs till längst ned i
 *   GamePanel. Denna innehåller i sin tur tre JLabels. Alla JLabels läggs 
 *   till från början, men de innehåller inget. När sedan en viss modul läggs
 *   ned på targetLocation så kontrolleras om det är rätt modul och om så är
 *   fallet så sätts en ny ikon till den aktuella JLabeln (setIcon()).
 *
 * 
 * SÖNDAG:
 * - releaseObject i Player har fått ett rejält lyft. Nu kollar den om det
 *   är en RocketModule som släpps i targetLocation, och om så är fallet så
 *   läggs den till i retreivedObjects (som jag har flyttat från GamePanel till
 *   Player eftersom det ju är Playern som har samlat ihop dem). Efter mycket
 *   om och men funkar detta som det ska (hoppas jag). Problemet med detta
 *   var när man körde restart(), då uppdaterades inte workshopen, men nu bör
 *   allt vara fixat. (ca 3 tim.)  Varje player har också en egen workshop
 *   vilket bör fungera mycket smidigt när vi inför 2 player.
 * 
 * - Fixat aboutWindow som öppnas när man klicka på en knapp. Spelet pausas,
 *   och resumas när about-fönstret stängs.
 * 
 * 
 * TOMS FÖRÄNDRINGAR ONSDAG-TORSDAG:
 * ONSDAG:
 * - Tagit bort koden för att flytta spelarna från GameWorld till respektive
 *   spelare. i GameWorld anropas metoden move() på både waiter och gubbe.
 *   Dessa gör vad de ska och anropar sedan den generella metoden
 *   moveGameActor() som ligger i GameActor, där själva flyttandet äger rum.
 * 
 * - Därmed också lagt in det där med att spöket bara skall flyttas var n:te
 *   tick i klassen Waiters tick()-metod (i stället för att som i mitt tidigare
 *   fulhack ha även den koden i världen).
 * 
 * - Jag har INTE bytt representation på världen från en char[][] till en
 *   String[]. Detta efter som den visuella representationen blir ÄNNU mer miss-
 *   ledande då. Ett tecken (med ravavstånd) är typ 2-3 ggr så högt som det är
 *   brett. Vitsen med att ändå byta skulle vara att det är lite enklare att
 *   snabbt redigera världen om man slipper byta tecken för tecken utan kan
 *   redigera lite större sjok åt gången. Vi kan diskutera detta.
 * 
 * - Av någon anledning var instansvariablerna player, worldActors och
 *   worldObjects inte deklarerade som private. Jag har fixat detta, med två
 *   följdändringar till mainWorld.getPlayer() i stället för mainWorld.player
 *   i GameAreaLabyrinth.
 * 
 * - I världen har jag lagt till instansvariabeln LinkedList worldFloorTiles,
 *   som innehåller alla korridorsrutor. I kombination med den nya metoden
 *   getRandomFreeLocation() använder jag listan för att slumpa fram start-
 *   positioner för gubbe, waiter och föremål.
 * 
 * - I världen har jag lagt in metoden Object getRandomItem(LinkedList l).
 *   Den används sedan i metoderna getRandomIcon och getRandomFreeLocation
 *   (se ovan).
 * 
 * - Jag har tagit bort pickUpObject() från spelarens moveTo()-metod och
 *   i stället skapat metoden pickUpReleaseObject() (döp den gärna till något
 *   bättre) som anropas när man trycker på space. Om man bär på något anropas
 *   relaseObject, annars pickUpObject.
 * 
 * - Renderat ny grafik, både stora bilder på raketen i olika stadier
 *   (NozzleOnly.png, NozzleFuselage.png, NozzleFuselageWings.png,
 *   CompleteRocket.png) och små ikoner för utpluttning i labyrinten.
 * 
 * - Gjort ett utkast till en splash screen (about2.gif)
 * 
 * TORSDAG:
 * - Skapat klasserna RocketFuselage, RocketNoseCone och RocketWings
 * 
 * - Lagt till en GameActor actorOnLocation för varje GameLocation,
 *   inkl get- och set-metoder.
 * 
 * - I moveTo() kollar jag om actorOnLocation för den nya rutan är
 *   skild från null. Då flyttar vi gubben dit. Annars skickas this (aktuell
 *   GameActor) och actorOnLocation till metoden handleCollision som tar
 *   som argument vilka actors som har stött ihop.
 * 
 * - För tillfället gör handleCollision inget mer än att skriva ut "Pang!".
 * 
 * - Har rensat lite i koden för GameActor, Ghost och Player. Det visade
 *   sig att spelaren och spöket flyttades med hjälp av olika metoder
 *   helt i onödan. Jag har gjort så att båda flyttas med hjälp av
 *   samma metod.
 * 
 * - Löste på egen hand problem med hur vi kommer åt den statiska mainWorld:
 *   I stället för:
 *   newLocation = getLocation().getMainWorld().getNewLocation(this);
 *   ...skriver vi så klart:
 *   newLocation = GameLocation.getMainWorld().getNewLocation(this);
 *   Jag har inte inte ändrat i GameActor än, så kan du se hur det funkar.
 * 
 * - Lagt till en GameLocation targetLocation för varje Player. Den sätts
 *   initialt till den ruta där spelaren börjar. Den fås naturligtvis
 *   genom metoden getTargetLocation som returnerar en GameLocation.
 * 
 * - Därmed också gjort att om char-matrisen innehåller t så ritas en särskild
 *   Floor-ruta ut (med mario.gif tills vidare).
 * 
 * - Lagt till en fungerande quit-knapp. Detta ledde till strul med focus.
 *   När knappen läggs till får den automatiskt focus.
 *   labyrinth.transferFocus() hjälpte inte heller eftersom transferFocus()
 *   INTE, som man skulle kunna tro av Mickes kod, sätter focus på den som
 *   tar emot meddelandet. I stället skickar den focus vidare till nästa
 *   element som är focusable. Metoden man skall anropa är requestFocus()!
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * TOMS DAMMSUGARSTRUL:
 * 
 * - Jag disablade båda spökena tills vidare...
 * - Likaså har jag hårdkodat in en hög hastighet (moveVelocity)
 *   hos sugaren för att det skall gå snabbare att testköra...
 * 
 * - Vi hade tänkt fel när vi räknade ut Direction, och räknade bakvänt. Vi
 *   sade om att location låg till vänster om newLocation skulle vi gå
 *   åt vänster. I stället skall vi så klart gå åt höger! Jag har rättat detta
 *   i getNewDirection(GameLocation l).
 * 
 * - Vårt andra tankefel var att vi när vi skulle börja linda upp
 *   travelledPath igen bara poppade den. Problemet är att det första
 *   som händer när vi går till en ny rutan är att den läggs till
 *   travelledPath. När vi poppar den får vi alltså bara tillbaks samma
 *   ruta som vi står på och SuperSucker fastnar. Nu har jag kört en extra
 *   pop() i getNextDirection() innan vi returnerar något.
 * 
 * - Nu funkar sugaren halv-OK, men den kraschar ibland så helt bra är den inte.
 *   Jag tror att det beror på mitt extra stack-poppande. Kanske inträffar
 *   kraschen när den kommer till slutet av sin upplindning av travelledPath?
 *   
 * 
 */
public class SpaceRace {

	/**
	 * The main method. Starts the game.
	 */
	public static void main(String[] args) {
		GameWindow mainWindow = new GameWindow();
	}
}
