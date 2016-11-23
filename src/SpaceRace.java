/**
 * @author Airaksinen, Tom & Wentzel, Jonatan
 *
 * The main class for the splendid game that will redefine the concept of 
 * electronic entertainment and hopefully contribute to world peace.
 * 
 */

/*
 * EFTER INL�MNING:
 * 
 * att g�ra:
 *  - Game starting in 3, 2, 1
 *  - Avbryt = avbryt vid inmatning av spel
 *  - Blink vid fel del
 *  - R�k innan uppskjut
 *  - Ljud
 *  - GUI f�r att byta f�rger + ikoner (actors + locations) (ist�llningar
 *    b�r sparas)
 * 
 * 
 * Jonatan:
 * 
 * - Uppritningen av hela raketen g�rs nu med nullayout, d.v.s stor f�r�ndring.
 *   Rakterna vibrerar i x-led med en hypad sinusfunktion och accelererar i
 *   y-led med en fager exponentialfunktion. GameWorkshop heter nu ist�llet
 *   RocketPanel.
 * 
 * - Spelarna kan nu �terigen g� diagonalt.Blir dock inte helt snyggt eftersom
 *   inga diagonala bilder finns. Det blir inte heller helt bra eftersom
 *   diagonalt verkligen betyder diagonalt, inte t.ex. v�nster om det finns
 *   en v�gg upp�t och man h�ller ner v�nster och upp�t (getNextLocation ser
 *   ju att det �r en v�gg snett upp�t v�nster)
 * 
 * - Uppl�gget f�r ikoner till GameLocation har �ndrats. Nu har alla subklasser
 *   till GameLocation en lokal variabel type som ber�ttar vilken typ en instans
 *   �r. Sedan har Wall resp. Floor statiska variabler f�r de olika typerna av
 *   ikoner. Vid Draw(g) anropas getImage() som kollar vilken typ instansen har
 *   och returnerar sedan r�tt bild.
 * 
 * F�RE INL�MNING:
 * 
 * 
 *    Jonatan har fixat f�ljande:
 * - Fixade till SuperSuckerns suck. Nu ska allt vara perfa!
 * 
 * - Fixade s� att save- och loadHighScore l�ser och skriver hela listan som
 *   ett objekt.
 * 
 * - Fixade s� att highscorelistan sorteras efter att e1 har lagts till.
 * 
 * - Bytte namn p� GameAreaLabyrinth till GameArea och finnkampen 2067 till
 *   SpaceRace
 * 
 * - Move f�r SuperSucker kanske beh�ver lite inside-kommentarer
 * 
 * - Allt annat f�rdigkodat (tror jag)
 * 
 * - Tyv�rr brakar spelet n�r SuperSuckern hittar dammet p� marken...
 * 
 * 1) Tom har fixat f�ljande:
 * - Lade in ett Instructions window (gl�m inte att uppdatera klassdiagrammet!)
 *   Tror jag kom ih�g att l�gga in alla grejer r�tt (mnemonics, ctrl, uppdatera
 *   hj�lptexter, titelrad i f�nstret), men dubbelkolla g�rna
 * 
 * - Fixade en BUTTON_COLOR i GameWindow, som anv�nds till knapparna. De �r nu
 *   n�got ljusare �n bakgrunden.
 * 
 * - D�pte om och st�dade bland bilderna i img-katalogen. Se separat mejl!
 * 
 * - Kommenterade till och med bokstaven G. B�rja med HighScoreEntry du!
 * 
 * - Lade till s� att HighScoreEntry implementerar Comparable och d�rmed kan
 *   sorteras automatiskt med "Collections.sort(highscorelistan)"
 *   (just den syntaxen). (Uppdatera klassdiagrammet med �ven denna nya
 *   implements-grej.)
 * 
 * - Skrev om addHighScores(e1, e2) i GameWindow till en i mitt tycke snyggare
 *   variant som bara beh�ver g� igenom listan en g�ng. Tidigare metod finns
 *   kvar precis ovanf�r, men �r bortkommenterad.
 * 
 * - �ndrade get&set-raden till
 * 
 *    // ------------------- Get&Sets of relevant variables -------------------
 *    
 *    (Tv� blankrader ovanf�r, en under)
 * 
 *    Jag har lagt in den i alla relevanta klasser (hoppas jag).
 * 
 * 
 * 2) Att-g�ra-1: Fixa enhetlighet n�r det g�ller f�ljande:
 * 
 * - Blankrad (som ovan) eller inte (som i Dust.java) mellan sista raden i v�r
 *   textbeskrivning och sluttaggen (asterisk-slash) i javadoc-kommentaren
 *   f�r klasser - JA, blankrad i kommentar till klasser!
 * 
 * - Skall vi ha blankrad mellan beskrivning och @param- och @return-rader?
 *   Nu �r det s� p� vissa st�llen, men inte p� andra. Jag tycker att det �r
 *   snyggt om man har m�nga params och returns, men det k�nns on�digt om
 *   det bara �r en s�dan efter sj�lva beskrivningen. �r det viktigt med samma
 *   princip �verallt? Kan den principen vara "om endast ett argument -> ingen
 *   blankrad"? - JA!
 * 
 * - Skall vi ha javadoc-kommentarer p� bara en rad? De allra flesta metoder
 *   tar antingen ett argument eller returnerar n�got. Enradskommentarerna
 *   riskerar att f�rsvinna lite och se ut som vanliga slash-slash-kommentarer.
 *   - Inga enradskommentarer!
 * 
 * - Ordning p� get och set, get f�rst - sen set. - JA!
 * 
 * 
 * 3) Att-g�ra-2: Fixa till f�ljande grejer:
 * 
 * - Kommentera instansvariabler med icke-uppenbara namn eller syften?
 * 
 * - Varf�r �r metoden createLocationMatrix i GameWorld private? Skadar inte,
 *   men inga andra metoder �r v�l privata? Byt till public? - FIXAT!
 * 
 * - Kan det faktum att HighScoreEntry nu implementerar Comparable anv�ndas
 *   �ven p� andra st�llen f�r att snygga till koden. Jag h�ngde inte riktigt
 *   med i sv�ngarna p� varf�r det skulle vara rank med i konstruktorerna
 *   (till entrysarna) och s�nt. F�rmodligen �r detta bara n�got gammalt arv,
 *   men du har b�st koll! - FIXAT!
 * 
 * 
 *  
 * 
 * 
 * 
 * Att g�ra:
 * - Fixa till bilderna s� att man kan se skillnad p� delarna
 *   (Vingar f�r sm� och otydliga, motor f�r stor, nozzlekonturen
 *    skall fyllas i s� hela fr�mre konen syns - inget M)
 * - Fixa gubbarna (bilder)
 * - Fixa s� att man inte kan �ppna fler kopior av f�nstren (high score m.fl.)
 * - Fixa ljudeffekter (dammsugaren: schlurrp!)
 * - Fixa m�jlighet att st�nga av ljudet
 * - Cleanare high-scorelista (sl�ng bara in b�da entries och sortera sedan)?
 * 
 * 
 * 
 * 
 * *********** TOMS PILL TIS 11/1 *************
 * - Nu kan man g� igenom sp�ket n�r det �r genomskinligt. (Opacity < 0.3f,
 *   vilket inneb�r problem eftersom sp�kenas opacitetsvariationer slumpas fram.
 *   En del sp�ken kanske man typ aldrig kan g� igenom, andra kanske man kan
 *   g� igenom typ hela tiden... Tror ocks� att vi borde fixa en b�ttre
 *   rutin f�r kollisionshantering. Tror min sl�ar ner spelet FETT!
 * 
 * - Lade till en decreaseScore i Player p� samma s�tt som increaseScore.
 *   Anropas med argumentet 100 om man g�r igenom ett sp�ke. Vissa kollisioner
 *   detekteras av n�gon anledning inte (eller s� minskas bara inte po�ngen).
 * 
 * - Fixade s� att dammsugaren snor den del man b�r p�. F�r varje steg som
 *   sugaren flyttar sig �r det 10 % chans att den sl�pper objektet (om
 *   det inte redan ligger n�got i den rutan).
 *
 * - Lade till relaseCarriedObject() och setCarriedObject(Gameobject obj)
 *   i SuperSucker.
 * 
 * - Lade till loseObjectToSucker() i Player. Anropas om man b�r p� n�got som
 *   dammsugaren snor.
 * 
 * - Lade till s� att dammsugaren �ven suger upp delar som den �ker �ver.
 * 
 * - Se punkterna inom OBS! nedan! Nu m�ste jag dra till Edvard!
 * 
 * OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! 
 * 
 * M�STE FIXA SPECIALFALLET MED DAMM... Skall man kunna plocka upp det?
 * Skall dammsugaren d� "suga upp" det om han krockar med en?
 * 
 * M�ste ocks� fixa vad som h�nder om sugaren har med sig en del som den
 * sugit upp fr�n marken och g�r in i en. D� skall han inte sno ens egen del
 * va? Eller?
 * 
 * OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS! OBS!
 * 
 * - Fusk (steal)
 * - Kaffemaskin, waiter
 * - Ljud
 * - Fixa st�rre v�rld - FIXAT
 * - N�tverk
 * - Fixa slumpen till floorImageList
 * - Flytande g�ng
 * - GameAreaWorkshop
 * - Fixa en ruta i workshop/panel d�r det man b�r p� visas - FIXAT, men inte
 * 			helt snyggt. Jag tror att vi borde fundera lite p� layouten. Det
 *   		blir inget bra med knapparna d�r de �r, vi borde nog s�tta dem
 *  		i north i st�llet, det b�rjar bli f�r lite plats f�r player1s
 * 			rymdskepp att �ka p�.
 * 
 *
 * - v�lja ok/avbryt med tangentbordet. - funkare ej...men space funkar...
 *   jag b�rjar fatta varf�r, enter funkar p� v�r egna meny, men inte p�
 *   typ JOptionDialogue eftersom vi inte har m�jlighet att / vet hur man
 *   �ndrar detta i dessa, och det verkar inte finnas n�gon bra konstruktor
 *   som tar argument f�r att �ndra detta, det verkar helt enkelt vara en
 *   miss i Java 1.4.2 (f�rhopnningsvis b�ttre i 1.5). Liknande problem 
 *   har vi i v�r egna dialogruta showGameAlternatives. D�r kan man inte
 *   ens anv�nda ShowMnemonics (som �r default i showOptionDialogue) f�r
 *   att anv�nda Alt-h f�r highScore etc.
 *
 *
 * - Quit-problemet vid nytt spel vid gameOver - FIXAT, av n�gon mystisk
 *   anledning exekverades else-satsen i showGameAlternative trots att alt
 *   fortfarande = 0. Hursom blir det bra n�r man ist�llet skriver else if
 *   (alt == 2).
 * - Kollisionshantering
 * - F�rb�ttra highscorelistan - FIXAT!
 * - Applet - F�rs�kt, problem med r�ttigheter f�r att �ppna bilder
 * - Symmetrisk v�rld - FIXAT! Man ska nu bara rita upp halva v�rlden.
 * 
 * - Utplaceringen av AboutWindow och HighScoreWindow g�rs nu n�gorlunda
 *   likadant. Dock borde vi se till att de g�rs exakt likadant!
 * 
 * - RocketModule m�ste inneh�lla en panelImage och en labyrinthImage. Jag
 *   la dock till dessa i GameObject (labyrinthImage hette f�rut bara image).
 *   Detta gjorde ocks� att koden i GameAreaWorkshop blev mycket snyggare
 *   n�r vi kollar vilken bild som ska l�ggas till i workshopen.
 * 
 * - Hmm...n�got mysko att kollisionshanteringen ligger i GameActor, k�nns
 *   som att den borde ha att g�ra med legalMovefunktionen som v�l ligger i
 *   GameWorld...
 *
 *   ************ JONATAN - TISDAG KV�LL ********''
 * - Fixade spelarnamn. Man f�r skriva in dem i b�rjan av varje spel
 *   Om man har restartad s� �r default namnen fr�n f�rra spelet. Detta l�stes
 *   v�l inte helt snyggt, men metoden setPlayerNames finns i GameWindow och
 *   kan anropas med inga argument (inga defaultnamn i rutan) eller med tv�
 *   str�ngar (defaulttexterna).
 * 
 * - Fixade s� att den som har mest po�ng vinner. Detta st�r i Congrats-rutan.
 * 
 * - Provspelade med min utbytesstudentgranne, han tycket spelet var grymt!
 * 
 * 
 * 
 *   *************TOMS STRUL TISDAG EFTERMIDDAG/KV�LL********
 * - Fixat olika ikoner f�r spelare 1 och 2. Tyv�rr f�r vi inte skicka
 *   en int till konstruktorn, och sedan v�lja bilder att skicka
 *   till superkonstruktorn efter den. Konstruktoranropet m�ste ju
 *   komma f�rst! Tredje g�ngen vi g�r i denna f�llla.... Ett alternativ
 *   �r att g�ra en mellanniv�, typ public GamePlayer createPlayer(int num)
 *   som s�ger return new Player(blah blah), men jag tyckte det blev lite
 *   ointuitivt, s� jag struntade i det tills vidare...
 * 
 * - Fixat insets p� f�nsterstorleken. Att det inte funkade f�rut berodde
 *   nog p� att vi inte hade k�rt pack().
 * 
 * - Bytt ordning p� knapparna i panelen s� att New Game �r �verst och Quit
 *   underst...
 * 
 * - Fixat svart bakgrund och vit, centrerad text i po�ngf�lten
 * 
 * - Gjort ett fulhack i changeRocketClearing som kollar om spelaren
 *   skickat upp 2 raketer (jag tycker att man skall vinna just d�r,
 *   n�r raketen n�r taket, och inte n�r man skjuter upp den). D� visas
 *   en enkel dialogruta. N�r man klickar OK startas spelet om.
 * 
 * - Kommenterat bort metoden createLocationMatrix2 (den gamla, innan
 *   vi gick �ver till str�ngar). Du kan ta bort den om du vill.
 * 
 * - �terst�llt s� att alla delar slumpas ut. Jag kommenterade bara bort h�rd-
 *   kodningen av alla nya raketdelar n�ra spelare 1. Bra att kunna �terst�lla
 *   senare.
 * 
 * - Lade in ett sp�ke. Det g�r assegt p� den h�r datorn. Inte minst n�r en
 *   raket skjuts upp!
 * 
 * - Redigerade att-g�ra-listan h�r ovanf�r. Rensade ut en hel del som redan
 *   �r fixat och lade in att vi skall g�ra en v�lkomstruta med instruktioner.
 * 
 * 
 * 
 * 
 * 
 * 
 *  
 * Att g�ra f�r Tom onsdag-torsdag:
 * - Rendera grafik (men inte allt)
 * - Byt v�rlden fr�n charmatris till str�ngarray  --- NEJ! Se nedan!
 * - Knappar (New Game, Restart, Quit, About)
 * 
 * Att g�ra f�r Jonatan l�-s�
 * - Byt v�rlden fr�n charmatris till str�ngarray - Jo! se v�rlden! - FIXAT!
 * 
 * - Ev. flytta ut "legalmove"-delen ur moveTo i GameActor till legalMove
 *   i GameWorld (ev. returnera int ist�llet, 0=ok, 1=v�gg, 2=actor iv�gen
 * 
 * - Ist�llet f�r att ta bort obj fr�n listan av obj i v�rlden kanske deras
 *   location ska s�ttas till null? D� slipps "removeObjectFromWorld" - FIXAT!
 * 
 * - Fler knappar: New Game, About, Restart (Save? Load?) - Delvis
 * 
 * - Tangentbordskommandon Ctrl-Q - Delvis
 * 
 * - Fusk!!! (manipulera legalMove()) (g� utanf�r labyrinten och sno
 *   den andres del. - Delvis
 * 
 * - Bygga upp raketerna, dvs h�nda n�got n�r man l�gger obj i targetrutan.
 *   - FIXAT!
 * 
 * 
 * JONATANS F�R�NDRINGAR L�RDAG-S�NDAG:
 * L�RDAG:
 * - B�rjade kolla p� dammsugaren, kom ingen vart.
 * 
 * - T�nkte implementera en restartknapp f�r effektivare testning.
 *   Blev f�rst�s mycket mer komplicerat �n f�rv�ntat. restart() lades till i
 *   GameAreaLabyrinth, och panelen k�nner numera till labyrinten (f�r att
 *   kunna restarta den). Tyv�rr g�rs detta inte helt snyggt, se konstruktorn
 *   f�r GameWindow som m�ste anv�nda panel.setLabyrinth(labyrinth) eftersom
 *   labyrintkonstruktorn beh�ver panelen och panelkonstruktorn beh�ver
 *   laybrinten. Detta tror jag tyder p� att vi har ett stort konceptuellt
 *   tankefel n�gonstans i uppl�gget med klasser (eller ocks� s� kanske vissa
 *   metoder bara ligger i fel klasser?)... Ctrl-n funkar ocks�
 *   f�r att restarta.
 * 
 * - Paus �r implementerat. knapp p stoppar respektive resumar timern i
 *   GameWindow. I denna klass finns en boolean som visar om spelet �r
 *   pausat eller ej. Metoderna som har med detta att g�ra anropar f�rst
 *   getTopLevelAncestor() f�r att h�mta det GameWindow som de ligger i (m�ste
 *   castas)
 * 
 * - Eventuellt borde det i b�da dessa l�gen (spelet pausat samt restartat)
 *   komma upp en liten text ("Game Paused, press p to resume" "Game restarted,
 *   starting in 3, 2, 1 seconds"). Fixade �ven lite font p� dessa meddelanden.
 * 
 * - Ett lurigt fusk �r implementerat! N�r man har pausat kan man styra gubben
 *   n�r man sedan trycker p igen s� "teleporteras" gubben. P� s� s�tt kan man
 *   t.ex. hinna f�rbi dammsugaren som annars kanske skulle blockera v�gen
 *   (vadd� bug som jag inte har en aning om hur man fixar? :)
 * 
 * - Fixade stringWorld som representerar v�rlden med String[] i st�llet f�r
 *   char[][]. Det blev fortfarande inte helt symmetriskt, men helt ok.
 *   F�rst k�rde jag med kommatecken som separator, men det s�g inte s� snyggt
 *   ut s� jag fixade till det med space som separator. Jag sparade dock koden
 *   till kommaseparationen som ser snyggare ut s� f�r vi besluta vilket som
 *   �r b�st. I och med detta fixade jag ocks� en lite st�rre v�rld.
 * 
 * - La till setResizable(false) i GameWindow. Detta gjorde ramarna till
 *   f�nstret n�got tunnare s� jag minskade f�nstrets storlek med 2x2 px 
 *   (vi borde kolla upp det d�r med "getBorderThickness").
 * 
 * - Satte tillbaks den gamla composite i SuperSucker, annars anv�nds alpha-
 *   blinkningen p� allt som man ritar sj�lv (t.ex. paus-texten).
 * 
 * - Behandlingen av upplockande och neds�ttande av objekt f�rb�ttrades.
 *   Nu anv�nds inte l�ngre metoderna addObject och removeObject i GameWorld.
 *   Ist�llet s�tts objektens location till null och vid utritningen av dessa
 *   kontrolleras om location �r null, om s� �r fallet ritas objektet ej ut.
 * 
 * - �ndrat uppl�gget i panelen f�r att kunna l�gga till RocketModules allt
 *   eftersom. Jag inf�rde GameAreaWorkshop som l�ggs till l�ngst ned i
 *   GamePanel. Denna inneh�ller i sin tur tre JLabels. Alla JLabels l�ggs 
 *   till fr�n b�rjan, men de inneh�ller inget. N�r sedan en viss modul l�ggs
 *   ned p� targetLocation s� kontrolleras om det �r r�tt modul och om s� �r
 *   fallet s� s�tts en ny ikon till den aktuella JLabeln (setIcon()).
 *
 * 
 * S�NDAG:
 * - releaseObject i Player har f�tt ett rej�lt lyft. Nu kollar den om det
 *   �r en RocketModule som sl�pps i targetLocation, och om s� �r fallet s�
 *   l�ggs den till i retreivedObjects (som jag har flyttat fr�n GamePanel till
 *   Player eftersom det ju �r Playern som har samlat ihop dem). Efter mycket
 *   om och men funkar detta som det ska (hoppas jag). Problemet med detta
 *   var n�r man k�rde restart(), d� uppdaterades inte workshopen, men nu b�r
 *   allt vara fixat. (ca 3 tim.)  Varje player har ocks� en egen workshop
 *   vilket b�r fungera mycket smidigt n�r vi inf�r 2 player.
 * 
 * - Fixat aboutWindow som �ppnas n�r man klicka p� en knapp. Spelet pausas,
 *   och resumas n�r about-f�nstret st�ngs.
 * 
 * 
 * TOMS F�R�NDRINGAR ONSDAG-TORSDAG:
 * ONSDAG:
 * - Tagit bort koden f�r att flytta spelarna fr�n GameWorld till respektive
 *   spelare. i GameWorld anropas metoden move() p� b�de waiter och gubbe.
 *   Dessa g�r vad de ska och anropar sedan den generella metoden
 *   moveGameActor() som ligger i GameActor, d�r sj�lva flyttandet �ger rum.
 * 
 * - D�rmed ocks� lagt in det d�r med att sp�ket bara skall flyttas var n:te
 *   tick i klassen Waiters tick()-metod (i st�llet f�r att som i mitt tidigare
 *   fulhack ha �ven den koden i v�rlden).
 * 
 * - Jag har INTE bytt representation p� v�rlden fr�n en char[][] till en
 *   String[]. Detta efter som den visuella representationen blir �NNU mer miss-
 *   ledande d�. Ett tecken (med ravavst�nd) �r typ 2-3 ggr s� h�gt som det �r
 *   brett. Vitsen med att �nd� byta skulle vara att det �r lite enklare att
 *   snabbt redigera v�rlden om man slipper byta tecken f�r tecken utan kan
 *   redigera lite st�rre sjok �t g�ngen. Vi kan diskutera detta.
 * 
 * - Av n�gon anledning var instansvariablerna player, worldActors och
 *   worldObjects inte deklarerade som private. Jag har fixat detta, med tv�
 *   f�ljd�ndringar till mainWorld.getPlayer() i st�llet f�r mainWorld.player
 *   i GameAreaLabyrinth.
 * 
 * - I v�rlden har jag lagt till instansvariabeln LinkedList worldFloorTiles,
 *   som inneh�ller alla korridorsrutor. I kombination med den nya metoden
 *   getRandomFreeLocation() anv�nder jag listan f�r att slumpa fram start-
 *   positioner f�r gubbe, waiter och f�rem�l.
 * 
 * - I v�rlden har jag lagt in metoden Object getRandomItem(LinkedList l).
 *   Den anv�nds sedan i metoderna getRandomIcon och getRandomFreeLocation
 *   (se ovan).
 * 
 * - Jag har tagit bort pickUpObject() fr�n spelarens moveTo()-metod och
 *   i st�llet skapat metoden pickUpReleaseObject() (d�p den g�rna till n�got
 *   b�ttre) som anropas n�r man trycker p� space. Om man b�r p� n�got anropas
 *   relaseObject, annars pickUpObject.
 * 
 * - Renderat ny grafik, b�de stora bilder p� raketen i olika stadier
 *   (NozzleOnly.png, NozzleFuselage.png, NozzleFuselageWings.png,
 *   CompleteRocket.png) och sm� ikoner f�r utpluttning i labyrinten.
 * 
 * - Gjort ett utkast till en splash screen (about2.gif)
 * 
 * TORSDAG:
 * - Skapat klasserna RocketFuselage, RocketNoseCone och RocketWings
 * 
 * - Lagt till en GameActor actorOnLocation f�r varje GameLocation,
 *   inkl get- och set-metoder.
 * 
 * - I moveTo() kollar jag om actorOnLocation f�r den nya rutan �r
 *   skild fr�n null. D� flyttar vi gubben dit. Annars skickas this (aktuell
 *   GameActor) och actorOnLocation till metoden handleCollision som tar
 *   som argument vilka actors som har st�tt ihop.
 * 
 * - F�r tillf�llet g�r handleCollision inget mer �n att skriva ut "Pang!".
 * 
 * - Har rensat lite i koden f�r GameActor, Ghost och Player. Det visade
 *   sig att spelaren och sp�ket flyttades med hj�lp av olika metoder
 *   helt i on�dan. Jag har gjort s� att b�da flyttas med hj�lp av
 *   samma metod.
 * 
 * - L�ste p� egen hand problem med hur vi kommer �t den statiska mainWorld:
 *   I st�llet f�r:
 *   newLocation = getLocation().getMainWorld().getNewLocation(this);
 *   ...skriver vi s� klart:
 *   newLocation = GameLocation.getMainWorld().getNewLocation(this);
 *   Jag har inte inte �ndrat i GameActor �n, s� kan du se hur det funkar.
 * 
 * - Lagt till en GameLocation targetLocation f�r varje Player. Den s�tts
 *   initialt till den ruta d�r spelaren b�rjar. Den f�s naturligtvis
 *   genom metoden getTargetLocation som returnerar en GameLocation.
 * 
 * - D�rmed ocks� gjort att om char-matrisen inneh�ller t s� ritas en s�rskild
 *   Floor-ruta ut (med mario.gif tills vidare).
 * 
 * - Lagt till en fungerande quit-knapp. Detta ledde till strul med focus.
 *   N�r knappen l�ggs till f�r den automatiskt focus.
 *   labyrinth.transferFocus() hj�lpte inte heller eftersom transferFocus()
 *   INTE, som man skulle kunna tro av Mickes kod, s�tter focus p� den som
 *   tar emot meddelandet. I st�llet skickar den focus vidare till n�sta
 *   element som �r focusable. Metoden man skall anropa �r requestFocus()!
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * TOMS DAMMSUGARSTRUL:
 * 
 * - Jag disablade b�da sp�kena tills vidare...
 * - Likas� har jag h�rdkodat in en h�g hastighet (moveVelocity)
 *   hos sugaren f�r att det skall g� snabbare att testk�ra...
 * 
 * - Vi hade t�nkt fel n�r vi r�knade ut Direction, och r�knade bakv�nt. Vi
 *   sade om att location l�g till v�nster om newLocation skulle vi g�
 *   �t v�nster. I st�llet skall vi s� klart g� �t h�ger! Jag har r�ttat detta
 *   i getNewDirection(GameLocation l).
 * 
 * - V�rt andra tankefel var att vi n�r vi skulle b�rja linda upp
 *   travelledPath igen bara poppade den. Problemet �r att det f�rsta
 *   som h�nder n�r vi g�r till en ny rutan �r att den l�ggs till
 *   travelledPath. N�r vi poppar den f�r vi allts� bara tillbaks samma
 *   ruta som vi st�r p� och SuperSucker fastnar. Nu har jag k�rt en extra
 *   pop() i getNextDirection() innan vi returnerar n�got.
 * 
 * - Nu funkar sugaren halv-OK, men den kraschar ibland s� helt bra �r den inte.
 *   Jag tror att det beror p� mitt extra stack-poppande. Kanske intr�ffar
 *   kraschen n�r den kommer till slutet av sin upplindning av travelledPath?
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
