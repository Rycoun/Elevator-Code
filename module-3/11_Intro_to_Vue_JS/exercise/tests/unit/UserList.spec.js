import { shallowMount, Wrapper } from "@vue/test-utils";
import UserList from "../../src/components/UserList.vue";
import { describe, it, beforeEach } from 'vitest';

import chai from "chai";
chai.should();

describe("UserList.vue", () => {
  /** @type Wrapper */
  let wrapper;

  // test user data
  const users = [
    {
      firstName: "John",
      lastName: "Smith",
      username: "jsmith",
      emailAddress: "jsmith@gmail.com",
      status: "Active"
    },
    {
      firstName: "Anna",
      lastName: "Bell",
      username: "abell",
      emailAddress: "abell@yahoo.com",
      status: "Active"
    },
    {
      firstName: "George",
      lastName: "Best",
      username: "gbest",
      emailAddress: "gbest@gmail.com",
      status: "Inactive"
    },
    {
      firstName: "Ben",
      lastName: "Carter",
      username: "bcarter",
      emailAddress: "bcarter@gmail.com",
      status: "Active"
    },
    {
      firstName: "Katie",
      lastName: "Jackson",
      username: "kjackson",
      emailAddress: "kjackson@yahoo.com",
      status: "Active"
    },
    {
      firstName: "Mark",
      lastName: "Smith",
      username: "msmith",
      emailAddress: "msmith@foo.com",
      status: "Inactive"
    }
  ];

  const search = {
    firstName: "",
    lastName: "",
    username: "",
    emailAddress: "",
    status: ""
  };

  beforeEach(() => {
    wrapper = shallowMount(UserList);
    wrapper.setData({
      users: users,
      search: search
    });
  });

  describe("Part one tests", () => {
    it("should display a list of users with correct data", () => {
      const rows = wrapper.findAll("#tblUsers tbody tr");
      rows.length.should.equal(7);
      let columns = rows.at(1).findAll("td");
      columns.at(0).text().should.equal(users[0].firstName);
      columns.at(1).text().should.equal(users[0].lastName);
      columns.at(2).text().should.equal(users[0].username);
      columns.at(3).text().should.equal(users[0].emailAddress);
      columns.at(4).text().should.equal(users[0].status);
    });
  });

  describe("Part two tests: Column Filters", () => {
    it("should set the search firstName property when the search changes", () => {
      const search = wrapper.find("#firstNameFilter");
      search.setValue("anna");
      wrapper.vm.search.firstName.should.equal("anna");
    });

    it("should set the search lastName property when the search changes", () => {
      const search = wrapper.find("#lastNameFilter");
      search.setValue("jackson");
      wrapper.vm.search.lastName.should.equal("jackson");
    });

    it("should set the search username property when the search changes", () => {
      const search = wrapper.find("#usernameFilter");
      search.setValue("msmith");
      wrapper.vm.search.username.should.equal("msmith");
    });

    it("should set the search emailAddress property when the search changes", () => {
      const search = wrapper.find("#emailFilter");
      search.setValue("bcarter@gmail.com");
      wrapper.vm.search.emailAddress.should.equal("bcarter@gmail.com");
    });

    it("should set the search status property when the search changes", () => {
      const search = wrapper.find("#statusFilter");
      search.setValue("Inactive");
      wrapper.vm.search.status.should.equal("Inactive");
    });
  });

  describe("Part three tests: Computed Properties", () => {
    it("should perform a case insensitive search of users by first name", async () => {
      const search = wrapper.find("#firstNameFilter");
      search.setValue("anna");
      await wrapper.vm.$nextTick();
      wrapper.vm.filteredList.length.should.equal(1);
    });

    it("should perform a case insensitive search of users by last name", async () => {
      const search = wrapper.find("#lastNameFilter");
      search.setValue("jackson");
      await wrapper.vm.$nextTick();
      wrapper.vm.filteredList.length.should.equal(1);
    });

    it("should perform a case insensitive search of users by username", () => {
      const search = wrapper.find("#usernameFilter");
      search.setValue("msmith");
      wrapper.vm.filteredList.length.should.equal(1);
    });

    it("should perform a case insensitive search of users by email address", async () => {
      const search = wrapper.find("#emailFilter");
      search.setValue("bcarter@gmail.com");
      await wrapper.vm.$nextTick();
      wrapper.vm.filteredList.length.should.equal(1);
    });

    it("should search users by inactive status", async () => {
      const search = wrapper.find("#statusFilter");
      search.setValue("Inactive");
      await wrapper.vm.$nextTick();
      wrapper.vm.filteredList.length.should.equal(2);
    });

    it("should search users by active status", async () => {
      const search = wrapper.find("#statusFilter");
      search.setValue("Active");
      await wrapper.vm.$nextTick();
      wrapper.vm.filteredList.length.should.equal(4);
    });

    it("should show all users when status search is blank", async () => {
      const search = wrapper.find("#statusFilter");
      search.setValue("");
      await wrapper.vm.$nextTick();
      wrapper.vm.filteredList.length.should.equal(6);
    });

    it("should be able to use multiple filters", async () => {
      const firstNameFilter = wrapper.find("#firstNameFilter");
      const lastNameFilter = wrapper.find("#lastNameFilter");
      firstNameFilter.setValue("n");
      lastNameFilter.setValue("e");
      await wrapper.vm.$nextTick();
      wrapper.vm.filteredList.length.should.equal(2);
    });
  });

  describe("Part four tests", () => {
    it("renders a row with the class of inactive if the user status equals Inactive", () => {
      wrapper.findAll(".inactive").length.should.equal(2);
    });
  });
});
