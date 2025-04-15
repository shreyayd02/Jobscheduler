'use client';

import { useState } from 'react';

export default function JobForm() {
  const [jobName, setJobName] = useState('');
  const [executionType, setExecutionType] = useState('immediate');
  const [scheduleTime, setScheduleTime] = useState('');
  const [timeZone, setTimeZone] = useState('Asia/Kolkata');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const jobData = {
      jobName,
      executionType,
      scheduleTime: executionType === 'specific' ? scheduleTime : null,
      timeZone: executionType === 'specific' ? timeZone : null,
    };

    try {
      setLoading(true);

      // ✅ Change this line:
      const response = await fetch('http://localhost:8080/api/jobs/schedule', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(jobData),
      });

      const result = await response.json();

      if (!response.ok) throw new Error(result?.error || 'Job submission failed');

      alert('✅ Job submitted successfully!');
      console.log(result);

      // Reset form
      setJobName('');
      setExecutionType('immediate');
      setScheduleTime('');
      setTimeZone('Asia/Kolkata');
    } catch (error) {
      alert('❌ Error submitting job. Check console for details.');
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="space-y-4 bg-white p-6 rounded-xl shadow-lg max-w-lg mx-auto mt-10"
    >
      <h2 className="text-2xl font-bold">Schedule a Job</h2>

      <div>
        <label className="block mb-1 font-medium">Job Name</label>
        <input
          type="text"
          value={jobName}
          onChange={(e) => setJobName(e.target.value)}
          className="w-full border border-gray-300 rounded p-2"
          required
        />
      </div>

      <div>
        <label className="block mb-1 font-medium">Execution Type</label>
        <select
          value={executionType}
          onChange={(e) => setExecutionType(e.target.value)}
          className="w-full border border-gray-300 rounded p-2"
        >
          <option value="immediate">Immediate</option>
          <option value="specific">Specific Time</option>
        </select>
      </div>

      {executionType === 'specific' && (
        <>
          <div>
            <label className="block mb-1 font-medium">Schedule Time</label>
            <input
              type="datetime-local"
              value={scheduleTime}
              onChange={(e) => setScheduleTime(e.target.value)}
              className="w-full border border-gray-300 rounded p-2"
              required
            />
          </div>

          <div>
            <label className="block mb-1 font-medium">Time Zone</label>
            <select
              value={timeZone}
              onChange={(e) => setTimeZone(e.target.value)}
              className="w-full border border-gray-300 rounded p-2"
            >
              <option value="Asia/Kolkata">Asia/Kolkata</option>
              <option value="UTC">UTC</option>
              <option value="America/New_York">America/New_York</option>
              <option value="Europe/London">Europe/London</option>
              {/* Add more time zones as needed */}
            </select>
          </div>
        </>
      )}

      <button
        type="submit"
        disabled={loading}
        className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition disabled:opacity-50"
      >
        {loading ? 'Submitting...' : 'Submit Job'}
      </button>
    </form>
  );
}
