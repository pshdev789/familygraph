package com.wundermancommerce.interviewtests.graph;

import java.util.HashSet;
import java.util.List;

public class Main {

	public static void main(String[] args) {
	RelationshipMapper relationMapper = new RelationshipMapper();
		
		List<Person> pplList = relationMapper.getPeople();
		Graph<Person,String> graph = relationMapper.createRelationshipGraph(pplList);
		relationMapper.getExtendedFamilyRecursive(graph, pplList.get(0), new HashSet<Person>());
	}

}
