package org.springframework.samples.yogogym.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

	private TrainingService trainingService;
	
	@Transactional
	public List<String> selectPlaylistType(Integer trainingId) {
		List<String> res = new ArrayList<>();
		List<String> playlistsVeryIntense = new ArrayList<>();
		playlistsVeryIntense.add("ID_PLAYLIST_VERY_INTENSE");
		List<String> playlistsIntense = new ArrayList<>();
		playlistsIntense.add("ID_PLAYLIST_INTENSE");
		List<String> playlistsModerated = new ArrayList<>();
		playlistsModerated.add("ID_PLAYLIST_MODERATED");
		List<String> playlistsLow = new ArrayList<>();
		playlistsLow.add("ID_PLAYLIST_LOW");
		Training t = this.trainingService.findTrainingById(trainingId);
		Collection<Routine> routines = t.getRoutines();
		Integer low = 0;
		Integer moderated = 0;
		Integer intense = 0;
		Integer veryIntense = 0;
		for(Routine r : routines) {
			Collection<RoutineLine> routineLine = r.getRoutineLine();
			for(RoutineLine rl : routineLine) {
				if(rl.getExercise().getIntensity().equals(Intensity.LOW))
					low++;
				if(rl.getExercise().getIntensity().equals(Intensity.MODERATED))
					moderated++;
				if(rl.getExercise().getIntensity().equals(Intensity.INTENSE))
					intense++;
				if(rl.getExercise().getIntensity().equals(Intensity.VERY_INTENSE))
					veryIntense++;
			}
		}
		if((low>moderated)&&(low>moderated)&&(low>moderated))
			res = playlistsLow;
		if((moderated>=low)&&(moderated>=intense)&&(moderated>=veryIntense))
			res = playlistsModerated;
		if((intense>low)&&(intense>moderated)&&(intense>veryIntense))
			res = playlistsIntense;
		if((veryIntense>low)&&(veryIntense>intense)&&(veryIntense>moderated))
			res = playlistsVeryIntense;
		
		return res;
		
	}
}
