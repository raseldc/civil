/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.converter;

import com.civil.detail.EmployeeDetail;
import com.civil.model.Employee;

/**
 *
 * @author rasel
 */



public class EmpolyeeConverter {
    static EmployeeDetail getDetail(Employee entity) throws Exception {
        if (entity == null) {
            return null;
        }
        EmployeeDetail detail = new EmployeeDetail();

        detail.setId(entity.getId());
        detail.setFirstName(entity.getFirstName());
        detail.setLastName(entity.getLastName());
        detail.setFatherName(entity.getFatherName());
        detail.setMotherName(entity.getMotherName());
        detail.setMobileNumber(entity.getMobileNumber());
        detail.setNid(entity.getNid());
        detail.setPassportNumber(entity.getPassportNumber());
        detail.setDrivingLicense(entity.getDrivingLicense());
        detail.setImageUrl(entity.getImageUrl());
        detail.setEmail(entity.getEmail());
        detail.setPermanentAddress(entity.getPermanentAddress());
        detail.setPresentAddress(entity.getPresentAddress());
        detail.setSex(entity.getSex());
        detail.setDob(entity.getDob());
        detail.setPlaceOfBirth(entity.getPlaceOfBirth());
        detail.setNationality(entity.getNationality());
        detail.setDegree(entity.getDegree());
        detail.setSubject(entity.getSubject());
        detail.setInstitution(entity.getInstitution());
        detail.setYear(entity.getYear());
        detail.setReligion(entity.getReligion());
        detail.setBirthCertificate(entity.getBirthCertificate());
        detail.setBloodGroup(entity.getBloodGroup());
        detail.setPost(entity.getPost());
        detail.setJoinDate(entity.getJoinDate());
        detail.setLeaveDate(entity.getLeaveDate());
        detail.setActive(entity.getActive());
        return detail;

    }

    static Employee getEntity(EmployeeDetail detail) {
        if (detail == null) {
            return null;
        }
        Employee entity = new Employee();

        entity.setId(detail.getId());
        entity.setFirstName(detail.getFirstName());
        entity.setLastName(detail.getLastName());
        entity.setFatherName(detail.getFatherName());
        entity.setMotherName(detail.getMotherName());
        entity.setMobileNumber(detail.getMobileNumber());
        entity.setNid(detail.getNid());
        entity.setPassportNumber(detail.getPassportNumber());
        entity.setDrivingLicense(detail.getDrivingLicense());
        entity.setImageUrl(detail.getImageUrl());
        entity.setEmail(detail.getEmail());
        entity.setPermanentAddress(detail.getPermanentAddress());
        entity.setPresentAddress(detail.getPresentAddress());
        entity.setSex(detail.getSex());
        entity.setDob(detail.getDob());
        entity.setPlaceOfBirth(detail.getPlaceOfBirth());
        entity.setNationality(detail.getNationality());
        entity.setDegree(detail.getDegree());
        entity.setSubject(detail.getSubject());
        entity.setInstitution(detail.getInstitution());
        entity.setYear(detail.getYear());
        entity.setReligion(detail.getReligion());
        entity.setBirthCertificate(detail.getBirthCertificate());
        entity.setBloodGroup(detail.getBloodGroup());
        entity.setPost(detail.getPost());
        entity.setJoinDate(detail.getJoinDate());
        entity.setLeaveDate(detail.getLeaveDate());
        entity.setActive(detail.getActive());
        return entity;

    }
}
