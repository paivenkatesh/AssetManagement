package com.hexaware.ams.service;

/*
 * Author: Venkatesh Pai
 * Date: 09-11-2024
 * Comment: Created & Implemented Service Request.
 */

import java.util.List;

import com.hexaware.ams.entity.ServiceRequest;

public class ServiceRequestServiceImp implements IServiceRequestService {

	@Override
	public List<ServiceRequest> getAllServiceRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceRequest getServiceRequestById(int serviceRequestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceRequest> getServiceRequestByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceRequest> getServiceRequestsByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addServiceRequest(ServiceRequest serviceRequest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateServiceRequestStatus(int serviceRequestId, String status) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int deleteServiceRequest(int serviceRequestId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
