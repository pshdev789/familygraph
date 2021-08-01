package com.wundermancommerce.interviewtests.graph;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class RelationshipMapperTest {

	@Test
	public void testGetAllPeople() {
		RelationshipMapper RelationshipMapper = new RelationshipMapper();

		List<Person> peopleList = RelationshipMapper.getPeople();

		assertEquals(12, peopleList.size());
	}

	@Test
	public void testGetRelationshipCount() {
		RelationshipMapper RelationshipMapper = new RelationshipMapper();
		List<Person> peopleList = RelationshipMapper.getPeople();

		Graph<Person, String> graph = RelationshipMapper.createRelationshipGraph(peopleList);

		Person person1 = RelationshipMapper.getPersonByEmail(peopleList, "bob@bob.com");
		int bobRelationshipsCount = RelationshipMapper.getRelationShipCount(person1, graph).size();

		Person person2 = RelationshipMapper.getPersonByEmail(peopleList, "jenny@toys.com");
		int jennyRelationshipsCount = RelationshipMapper.getRelationShipCount(person2, graph).size();

		Person person3 = RelationshipMapper.getPersonByEmail(peopleList, "nigel@marketing.com");
		int nigelRelationshipsCount = RelationshipMapper.getRelationShipCount(person3, graph).size();

		Person person4 = RelationshipMapper.getPersonByEmail(peopleList, "alan@lonely.org");
		int alanRelationshipsCount = RelationshipMapper.getRelationShipCount(person4, graph).size();

		assertEquals(4, bobRelationshipsCount);
		assertEquals(3, jennyRelationshipsCount);
		assertEquals(2, nigelRelationshipsCount);
		assertEquals(0, alanRelationshipsCount);
	}

	@Test
	public void testGetExtendedFamily() {
		RelationshipMapper RelationshipMapper = new RelationshipMapper();
		List<Person> peopleList = RelationshipMapper.getPeople();

		Graph<Person, String> graph = RelationshipMapper.createRelationshipGraph(peopleList);

		Person person1 = RelationshipMapper.getPersonByEmail(peopleList, "bob@bob.com");
		Person person2 = RelationshipMapper.getPersonByEmail(peopleList, "jenny@toys.com");

		int bobExtendedFamilyCount = RelationshipMapper
				.getExtendedFamilyRecursive(graph, person1, new HashSet<Person>()).size();
		int jennyExtendedFamilyCount = RelationshipMapper
				.getExtendedFamilyRecursive(graph, person2, new HashSet<Person>()).size();

		assertEquals(4, bobExtendedFamilyCount);
		assertEquals(4, jennyExtendedFamilyCount);

	}
}
