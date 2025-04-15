'use client'

import { useState } from 'react'
import api from '../../../utils/axios';  // Ensure the path is correct

const timeZones = [
  'UTC', 'Asia/Kolkata', 'America/New_York', 'Europe/London', 'Asia/Tokyo'
]

const frequencies = ['Hourly', 'Daily', 'Weekly', 'Monthly']

export default function JobSchedulerForm() {
  // States for existing fields
  const [jobType, setJobType] = useState('one-time')
  const [timezone, setTimezone] = useState('UTC')
  const [frequency, setFrequency] = useState('Daily')
  
  // New states for additional fields
  const [dateTime, setDateTime] = useState('')
  const [binaryUrl, setBinaryUrl] = useState('')
  const [kafkaMetadata, setKafkaMetadata] = useState('')

  // Handle form submission
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    // Prepare the job data payload.
    const payload = {
      jobName: "Sample Job",      // Use a relevant name for the job
      binaryPath: binaryUrl,     // Binary URL or path to the .jar/.npm
      scheduleTime: dateTime,    // Schedule time
      status: "scheduled",       // Set status as "scheduled"
      recurring: jobType === "recurring",  // Check if the job is recurring
      frequency: jobType === "recurring" ? frequency : undefined, // Include frequency if recurring
      timezone,
    }

    try {
      // Send the payload to the backend endpoint using Axios.
      const response = await api.post('/api/jobs/schedule', payload)
      console.log("Job scheduled successfully:", response.data)
      alert("Job scheduled successfully!")
    } catch (error: any) {
      console.error("Error scheduling job:", error)
      alert("Failed to schedule job. Check console for details.")
    }
  }

  return (
    <div className="max-w-2xl mx-auto p-6">
      <h1 className="text-2xl font-bold mb-6">Schedule a Job</h1>
      <form className="space-y-6" onSubmit={handleSubmit}>
        <div>
          <label className="block mb-2 font-semibold">Job Type</label>
          <select
            value={jobType}
            onChange={e => setJobType(e.target.value)}
            className="w-full p-2 border rounded"
          >
            <option value="one-time">One-Time</option>
            <option value="immediate">Immediate</option>
            <option value="delayed">Delayed</option>
            <option value="recurring">Recurring</option>
          </select>
        </div>

        {/* Date & Time Picker */}
        {(jobType === 'one-time' || jobType === 'recurring') && (
          <div>
            <label className="block mb-2 font-semibold">Select Date & Time</label>
            <input 
              type="datetime-local" 
              className="w-full p-2 border rounded"
              value={dateTime}
              onChange={e => setDateTime(e.target.value)}
              required
            />
          </div>
        )}

        {/* Timezone */}
        {jobType !== 'immediate' && (
          <div>
            <label className="block mb-2 font-semibold">Time Zone</label>
            <select
              value={timezone}
              onChange={e => setTimezone(e.target.value)}
              className="w-full p-2 border rounded"
            >
              {timeZones.map((zone, index) => (
                <option key={index} value={zone}>{zone}</option>
              ))}
            </select>
          </div>
        )}

        {/* Frequency */}
        {jobType === 'recurring' && (
          <div>
            <label className="block mb-2 font-semibold">Frequency</label>
            <select
              value={frequency}
              onChange={e => setFrequency(e.target.value)}
              className="w-full p-2 border rounded"
            >
              {frequencies.map((freq, index) => (
                <option key={index} value={freq}>{freq}</option>
              ))}
            </select>
          </div>
        )}

        <div>
          <label className="block mb-2 font-semibold">Binary URL (jar/npm)</label>
          <input
            type="text"
            placeholder="e.g. https://example.com/myapp.jar"
            className="w-full p-2 border rounded"
            value={binaryUrl}
            onChange={e => setBinaryUrl(e.target.value)}
            required
          />
        </div>

        <button
          type="submit"
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          Schedule Job
        </button>
      </form>
    </div>
  )
}
