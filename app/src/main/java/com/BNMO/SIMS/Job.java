package com.BNMO.SIMS;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Job {
    private String name;
    private int salary;
    private static Map<String, Integer> jobs = new HashMap<>();

    public Job(String name, int salary) {
        this.name = name;
        this.salary = salary;

        jobs.put("Badut Sulap", 15);
        jobs.put("Koki", 30);
        jobs.put("Polisi", 35);
        jobs.put("Programmer", 45);
        jobs.put("Dokter", 30);
        jobs.put("Tentara", 10);
        jobs.put("PNS", 30);
        jobs.put("Teknisi", 20);
        jobs.put("Pramusaji", 15);
        jobs.put("Pilot", 55);
        jobs.put("Apoteker", 40);
        jobs.put("Guru", 10);
        jobs.put("Fotografer", 20);
        jobs.put("Pemadam Kebakaran", 20);
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Map<String, Integer> getJobs() {
        return jobs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setJobs(Map<String, Integer> jobs) {
        this.jobs = jobs;
    }

    public static void printListOfJobs() {
        System.out.println("List of Jobs:");
        int i = 1;
        for (Map.Entry<String, Integer> entry : jobs.entrySet()) {
            System.out.println(i + ". " + entry.getKey() + ", gaji: " + entry.getValue());
            i++;
        }
    }

    public static boolean isJobExist(String jobName) {
        ArrayList<String> validJobs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : jobs.entrySet()) {
            validJobs.add(entry.getKey().toLowerCase().replaceAll("\\s+", ""));
        }

        return validJobs.contains(jobName.toLowerCase().replaceAll("\\s+", ""));
    }
}
