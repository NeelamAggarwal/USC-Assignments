Create table building(
building_ID varchar2(5) Primary Key, 
building_name Varchar2(50),
shape MDSYS.SDO_GEOMETRY,
building_on_fire varchar2(5));

INSERT INTO USER_SDO_GEOM_METADATA 
  VALUES (
  'building',
  'shape',
  SDO_DIM_ARRAY(   -- 20X20 grid
    SDO_DIM_ELEMENT('X', 0, 100, 1),
    SDO_DIM_ELEMENT('Y', 0, 100, 1)
     ),
  NULL   -- SRID
);

CREATE INDEX building_index
   ON building(shape)
   INDEXTYPE IS MDSYS.SPATIAL_INDEX;
   

Create table hydrant(
hydrant_ID varchar2(5) Primary Key, 
coord MDSYS.SDO_GEOMETRY);

INSERT INTO USER_SDO_GEOM_METADATA 
  VALUES (
  'hydrant',
  'coord',
  SDO_DIM_ARRAY(   -- 20X20 grid
    SDO_DIM_ELEMENT('X', 0, 100, 1),
    SDO_DIM_ELEMENT('Y', 0, 100, 1)
     ),
  NULL   -- SRID
);

CREATE INDEX hydrant_index
   ON hydrant(coord)
   INDEXTYPE IS MDSYS.SPATIAL_INDEX;   
                           
                      