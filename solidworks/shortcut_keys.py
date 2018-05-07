"""
Jonathan Zerez, Hyegi Bang
SoftDes, Spring 2018

This script is simply a dictionary that maps specific letters to their
respective SOLIDWORKS shortcut names.

Sketch shortcuts:
Smart Dimension: s
Measure: e
Line: l
Circle: c
Rectangle: r
extrude: z
revolve: x
cut: v
fillet: b
escape: escape

Assembly shortcuts:
Measure: e
Mate: w
Mass Properties: q
Interference detection: i
Open new part: NO SHORTCUT
exploded view: t
plane: p
center of mass: o
escape: escape

"""

shortcut_key_codes = {"s": "Smart Dimension",
                     "e" : "Measure",
                     "l" : "Line",
                     "c" : "Circle",
                     "r" : "Rectangle",
                     "z" : "extrude",
                     "x" : "revolve",
                     "v" : "cut",
                     "b" : "fillet",
                    "esc": "escape",
                     "w" : "Mate",
                     "q" : "Mass Properties",
                     "i" : "Interference detection",
                     "NO SHORTCUT" : "Open new part",
                     "t" : "exploded view",
                     "p" : "plane",
                     "o" : "center of mass"
                    }
