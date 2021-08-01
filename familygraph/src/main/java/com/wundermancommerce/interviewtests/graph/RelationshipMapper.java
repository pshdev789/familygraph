package com.wundermancommerce.interviewtests.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RelationshipMapper {

	/** Read CSV file to retrieveAllPeople **/
	public List<Person> getPeople() {
		List<Person> people = new ArrayList<>();
		try {

			Path path = Paths.get("src/test/resources/people.csv");
			List<String> read1 = Files.readAllLines(path);

			for (String read : read1) {
				if (read != null) {
					String[] peopleArr = read.split(",");
					Person person = new Person(peopleArr[0], peopleArr[1], Integer.valueOf(peopleArr[2]));
					people.add(person);
				}
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return people;
	}

	/** Read CSV file and retrieveRe;ationship and Map to a Graph **/
	public Graph<Person, String> createRelationshipGraph(List<Person> list) {

		Graph<Person, String> graph1 = new AdjacencyMapGraph<>();
		try {

			Path path = Paths.get("src/test/resources/relationships.csv");
			List<String> read1 = Files.readAllLines(path);

			list.stream().forEach(person -> {
				graph1.insertVertex(person);
			});

			for (String read : read1) {

				if (read != null && !read.isEmpty()) {
					String[] relationshipArr = read.split(",");
					Person p1 = getPersonByEmail(list, relationshipArr[0]);
					Person p2 = getPersonByEmail(list, relationshipArr[2]);

					graph1.insertUndirected(p1, p2, relationshipArr[1]);

				}

			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return graph1;
	}

	/** Filter Person by Email **/
	public Person getPersonByEmail(List<Person> peopleList, String email) {
		List<Person> listPeople = peopleList.stream().filter(people -> people.getEmail().equals(email))
				.collect(Collectors.toList());
		if (null != listPeople && !listPeople.isEmpty()) {
			return listPeople.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Person> getRelationShipCount(Person person1, Graph<Person, String> graph) {

		Iterable iterable = graph.outNeighbors(person1);

		List<Person> neighbours = (List<Person>) StreamSupport.stream(iterable.spliterator(), false)
				.collect(Collectors.toList());

		return neighbours;
	}

	/** Perform a recursiveDFS and Retrieve all connected Family members when the root is specified **/

	public Set<Person> getExtendedFamilyRecursive(Graph<Person, String> graph, Person root, Set<Person> visited) {
		System.out.println(root);
		if (visited.isEmpty()) {
			visited = new LinkedHashSet<Person>();
		}
		visited.add(root);
		@SuppressWarnings("rawtypes")
		Iterable iterable = graph.outNeighbors(root);

		@SuppressWarnings("unchecked")
		List<Person> neighbours = (List<Person>) StreamSupport.stream(iterable.spliterator(), false)
				.collect(Collectors.toList());

		for (Person n : neighbours) {
			String relationType = (String) graph.getLabel(root, n);
			
			if (!visited.contains(n) && relationType != null && relationType.equals("FAMILY")) {

				getExtendedFamilyRecursive(graph, n, visited);
			}
		}
		return visited;
	}

}
