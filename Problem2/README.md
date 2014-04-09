##PrethesesGrouper

Groups text, as delineated by parentheses, and outputs the form of these groups to the screen as a picture.

Example of "form:" (Hello, world!) => (-------------)

** More Examples **

{oh [boy] (I am having) (<so> much) fun matching 'symbols'}
    [---]
          (-----------)
                         <-->
                        (---------)
{---------------------------------------------------------}

(Hello (world)
       (-----)
(  <-- UNMATCHED

(Hello (world))}  ]
       (-----)
(-------------)
               }  <-- UNMATCHED
                  ]  <-- UNMATCHED

(Hello [th[(e)re]] (World) '!')
           (-)
          [-----]
       [---------]
                   (-----)
(-----------------------------)

This <is> sparta! (or, [perhaps,] some other {place<city, village, country>})
     <-->
                       [--------]
                                                   <---------------------->
                                             {-----------------------------}
                  (---------------------------------------------------------)

This (is sparta]
     (  <-- UNMATCHED
An illegal character, ']', was found at position 15 of the input.

This (is sparta]
     (  <-- UNMATCHED
An illegal character, ']', was found at position 15 of the input.

This [(is sparta][{<
      (  <-- UNMATCHED
An illegal character, ']', was found at position 16 of the input.
                   <  <-- UNMATCHED
                  {  <-- UNMATCHED
                 [  <-- UNMATCHED
     [  <-- UNMATCHED

