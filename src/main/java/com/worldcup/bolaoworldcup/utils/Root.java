package com.worldcup.bolaoworldcup.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public
class Root{
   public int match_week;
   public List<GameBrasileiraoDto> games;
}