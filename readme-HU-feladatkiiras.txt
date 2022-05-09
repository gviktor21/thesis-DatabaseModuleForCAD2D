Az eredeti feladatkiírás:
A műszaki tervezőprogramok kapcsán elsősorban térinformatikai vagy épületgépészeti alkalmazásoknál van arra szükség, hogy rajzelemekhez plusz információt rendeljünk. A hallgató feladata egy olyan adatbázis-modul készítése egy 2D műszaki rajzolóprogramhoz, amely lehetőséget nyújt a felhasználó számára, hogy az egyes rajzelemekhez plusz információt rendeljen. Ilyen információ lehet például csővezetékek anyaga, áramköri rajzoknál a típus megnevezése, műszaki paraméterek megadása és az alkatrész jelölése. A feladat egy olyan adatbázis-modul elkészítése, amely lehetőséget nyújt a felhasználónak arra, hogy

meghatározza, hogy adott rajzelemhez milyen jellemzőket szeretne hozzávenni,
a rajzlemeket és jellemzőit egy relációs adatbázisban kezelje,
a rajzelemek jellemzőit meg lehessen jeleníteni a rajzolóprogramon belül (párbeszédablakban vagy jelentés formájában).
Az adatbázis készítése során fontos, hogy az az alkalmazással együtt, és ne külön szerverként működjön. Ilyen célt szolgálnak az ún. in-memory adatbázisok (pl. SQLite, HSQLDB).

A modult Java nyelven kell elkészíteni és illeszkednie kell a 2D rajzolóprogram eddigi moduljaihoz. HSQLDB adatbázis-kezelő-rendszer volt használva.
A programhoz volt egy alapprogram, és ahhoz kellett illeszkedni. Az alapprogramhoz több független modu készült.
Részletek a thesis-hu.pdf-ben.

Megjegyzés: Java 8 elötti verzióval készült a program.